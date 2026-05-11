#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import argparse
import json
import os
import re
import smtplib
from datetime import datetime
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from pathlib import Path
from zoneinfo import ZoneInfo

import requests
from bs4 import BeautifulSoup


BASE_URL = "https://www.cm9.eprs.jp/shinagawa/web"

INDEX_URL = f"{BASE_URL}/index.jsp"

SEARCH_URL = (
    f"{BASE_URL}/rsvWOpeInstSrchVacantAction.do"
)

DAILY_URL = (
    f"{BASE_URL}/rsvWOpeUnreservedDailyAction.do"
)

AJAX_URL = (
    f"{BASE_URL}/rsvWOpeUnreservedSearchAjaxAction.do"
)

LOGIN_PAGE_URL = (
    f"{BASE_URL}/rsvWTransUserLoginAction.do"
)

LOGIN_URL = (
    f"{BASE_URL}/rsvWUserAttestationLoginAction.do"
)

RESERVE_APPLY_URL = (
    f"{BASE_URL}/rsvWOpeReservedApplyAction.do"
)

RESERVE_AGREEMENT_URL = (
    f"{BASE_URL}/rsvWInstUseruleRsvApplyAction.do"
)

RESERVE_CONFIRM_URL = (
    f"{BASE_URL}/rsvWInstRsvApplyAction.do"
)

OUTPUT_DIR = Path("output")
OUTPUT_DIR.mkdir(parents=True, exist_ok=True)

TRACE_FILE = OUTPUT_DIR / "trace.txt"

LOGIN_HTML_FILE = (
    OUTPUT_DIR / "login_response.html"
)

DAILY_HTML_FILE = (
    OUTPUT_DIR / "daily_page.html"
)

JSON_FILE = (
    OUTPUT_DIR / "results.json"
)

AGREEMENT_HTML_FILE = (
    OUTPUT_DIR / "agreement_page.html"
)


JST = ZoneInfo("Asia/Tokyo")


AREA_MAP = {
    "荏原": "1100_0",
    "大井": "1200_0",
    "大崎": "1300_0",
    "品川": "1400_0",
    "八潮": "1500_0",
    "すべて": "0000_0",
}

ACTIVITY_MAP = {
    "バド": "31000000_31010600",
    "バスケ": "31000000_31010200",
    "卓球": "31000000_31010800",
    "バレ": "31000000_31010400",
    "テニス": "31000000_31010100",
    "フットサル": "31000000_31010300",
    "すべて": "31000000",
}


TARGET_MANSIONS = {
    "第三日野小学校",
    "第四日野小学校",
    "日野学園",
    "荏原第六中学校",
    "小山台小学校",
    "小山小学校",
    "後地小学校",
}


SMTP_HOST = "smtp.mail.yahoo.co.jp"
SMTP_PORT = 465
SMTP_USER = "tl2019@yahoo.co.jp"

def trace(msg):

    print(msg)

    with open(
        TRACE_FILE,
        "a",
        encoding="utf-8"
    ) as f:

        f.write(msg + "\n")


def jst_today():

    return datetime.now(JST).strftime(
        "%Y-%m-%d"
    )


def parse_args():

    parser = argparse.ArgumentParser()

    parser.add_argument("--start-date", default=jst_today())

    parser.add_argument("--area", default="大崎")

    parser.add_argument("--activity", default="バド")

    parser.add_argument("--limit", type=int, default=100)

    parser.add_argument("--mail-to", default="tl2019@yahoo.co.jp")

    parser.add_argument("--period-days", default="31")

    parser.add_argument("--date-mode", default="4")

    parser.add_argument("--weekdays", default="5,6,7,9")

    parser.add_argument("--apply-num", default="15")

    return parser.parse_args()


def login(session):

    trace("开始 login()")

    #
    # 进入登录页
    #

    r = session.post(
        LOGIN_PAGE_URL,
        data={
            "displayNo": "pawab2000"
        },
        timeout=30,
    )

    r.encoding = "cp932"

    soup = BeautifulSoup(
        r.text,
        "html.parser"
    )

    login_jkey = soup.find(
        "input",
        {"name": "loginJKey"}
    ).get("value")

    trace(
        f"loginJKey_length="
        f"{len(login_jkey)}"
    )

    payload = [

        (
            "userId",
            os.getenv(
                "SHINAGAWA_USER_ID"
            )
        ),

        (
            "password",
            os.getenv(
                "SHINAGAWA_PASSWORD"
            )
        ),

        ("fcflg", ""),

        ("displayNo", "pawab2100"),

        ("loginJKey", login_jkey),
    ]

    password = os.getenv(
        "SHINAGAWA_PASSWORD",
        ""
    )

    for ch in password:

        payload.append(
            (
                "loginCharPass",
                ch
            )
        )

    r = session.post(
        LOGIN_URL,
        data=payload,
        timeout=30,
    )

    r.encoding = "cp932"

    LOGIN_HTML_FILE.write_text(
        r.text,
        encoding="utf-8"
    )

    ok = (
        "ログアウト" in r.text
    )

    trace(
        f"login_success={ok}"
    )

    return ok


def build_search_payload(
    args,
    activity
):

    payload = [

        ("date", args.date_mode),

        ("daystart", args.start_date),

        ("days", args.period_days),

        ("dayofweekClearFlg", "0"),

        ("timezoneClearFlg", "0"),

        (
            "selectAreaBcd",
            AREA_MAP[args.area]
        ),

        ("selectIcd", ""),

        (
            "selectPpsClPpscd",
            activity
        ),

        ("displayNo", "pawab2000"),

        ("displayNoFrm", "pawab2000"),
    ]

    for w in args.weekdays.split(","):
        payload.append(("dayofweek", w))

    return payload


def extract_form_data(html):

    m = re.search(
        r'var formData = (\{.*?\});',
        html,
        re.S
    )

    if not m:

        trace(
            "formData missing"
        )

        return None

    return json.loads(
        m.group(1)
    )


def parse_hours(
    start_time,
    end_time
):

    try:

        st = datetime.strptime(
            start_time,
            "%H:%M"
        )

        et = datetime.strptime(
            end_time,
            "%H:%M"
        )

        return (
            et - st
        ).seconds / 3600

    except Exception:

        return 0


def fetch_results(
    session,
    form_data,
    limit
):

    results = []

    seen = set()

    offset = 0

    headers = {

        "X-Requested-With":
            "XMLHttpRequest",

        "Referer":
            DAILY_URL,

        "Origin":
            "https://www.cm9.eprs.jp",

        "Accept":
            "application/json, text/javascript, */*; q=0.01",

        "Content-Type":
            "application/x-www-form-urlencoded; charset=UTF-8",

        "User-Agent":
            "Mozilla/5.0",
    }

    while True:

        form_data["offset"] = offset

        trace(
            f"ajax offset={offset}"
        )

        r = session.post(
            AJAX_URL,
            data=form_data,
            headers=headers,
            timeout=30,
        )

        r.encoding = "cp932"

        data = r.json()

        rows = data.get(
            "results",
            []
        )

        trace(
            f"rows={len(rows)}"
        )

        if not rows:
            break

        for item in rows:

            use = str(
                item.get(
                    "useYmd",
                    ""
                )
            )

            if len(use) != 8:
                continue

            start_time = item.get(
                "sJTime2",
                ""
            )

            end_time = item.get(
                "eJTime2",
                ""
            )

            date_str = (
                f"{use[:4]}-"
                f"{use[4:6]}-"
                f"{use[6:8]}"
            )

            mansion = item.get(
                "bcdNm",
                ""
            )

            facility = item.get(
                "icdNm",
                ""
            )

            key = (
                date_str,
                mansion,
                facility,
                start_time,
                end_time,
            )

            if key in seen:
                continue

            seen.add(key)

            results.append({

                "date":
                    date_str,

                "mansion":
                    mansion,

                "facility":
                    facility,

                "start":
                    start_time,

                "end":
                    end_time,

                "duration_hours":
                    parse_hours(
                        start_time,
                        end_time
                    ),

                "raw": item,
            })

        next_offset = data.get(
            "next"
        )

        if not next_offset:
            break

        next_offset = int(
            next_offset
        )

        if next_offset <= offset:
            break

        offset = next_offset

        if offset >= limit:
            break

    return results


def do_search(
    session,
    args,
    activity
):

    session.post(
        SEARCH_URL,
        data=build_search_payload(
            args,
            activity
        ),
        timeout=30,
    )

    r = session.post(
        DAILY_URL,
        data={
            "displayNo":
                "prwrc2000"
        },
        timeout=30,
    )

    r.encoding = "cp932"

    DAILY_HTML_FILE.write_text(
        r.text,
        encoding="utf-8"
    )

    form_data = extract_form_data(
        r.text
    )

    if not form_data:
        return []

    return fetch_results(
        session,
        form_data,
        limit=args.limit
    )


def filter_current_month(results):

    prefix = jst_today()[:7]

    return [

        r for r in results

        if r["date"].startswith(
            prefix
        )
    ]


def find_critical(results):

    candidates = []

    for r in results:

        if r["mansion"] not in TARGET_MANSIONS:
            continue

        if r["duration_hours"] < 2:
            continue

        if r["duration_hours"] > 4:
            continue

        start_hour = int(
            r["start"].split(":")[0]
        )

        afternoon_score = (
            1 if start_hour >= 13 else 0
        )

        candidates.append({

            **r,

            "_sort": (
                -afternoon_score,
                -r["duration_hours"],
                r["date"],
                r["start"],
            )
        })

    candidates.sort(
        key=lambda x: x["_sort"]
    )

    for c in candidates:
        c.pop("_sort", None)

    return candidates


def open_reservation_agreement(
    session,
    candidate,
    args,
    activity
):

    raw = candidate["raw"]

    #
    # 读取 daily 页面
    #

    daily_html = DAILY_HTML_FILE.read_text(
        encoding="utf-8"
    )

    soup = BeautifulSoup(
        daily_html,
        "html.parser"
    )

    payload = {}

    #
    # ✅ 1. 复制所有 hidden input
    #

    for tag in soup.find_all("input"):

        name = tag.get("name")
        if not name:
            continue

        payload[name] = tag.get("value", "")

    #
    # ✅ 2. 覆盖 search 条件（关键）
    #

    payload.update({

        "date": args.date_mode,
        "daystart": args.start_date,
        "days": args.period_days,

        "dayofweekClearFlg": "0",
        "timezoneClearFlg": "0",

        "selectAreaBcd": AREA_MAP[args.area],
        "selectIcd": "",

        "selectPpsClPpscd": activity,
    })

    #
    # ✅ 3. 覆盖 reservation target
    #
    payload.update({

        "selectUseYmd":
            raw.get("useYmd", ""),

        "selectBldCd":
            raw.get("bcd", ""),

        "selectInstCd":
            raw.get("icd", ""),

        "selectPpsCd":
            raw.get("ppsCd", ""),

        "selectPpsClsCd":
            raw.get("ppsClsCd", ""),

        "selectStimeZoneNo":
            str(raw.get("tZoneNo", "")),

        "selectEtimeZoneNo":
            str(raw.get("tZoneNo", "")),

        "selectStime":
            raw.get("sTime", ""),

        "selectEtime":
            raw.get("eTime", ""),

        "selectApplyNum": args.apply_num,

        #
        # ✅ 关键新增
        #

        "availableTZoneNo":
            str(raw.get("tZoneNo", "")),

        "availableSTime":
            raw.get("sTime", ""),

        "availableETime":
            raw.get("eTime", ""),
    })

    #
    # ✅ 4. 修正 displayNo（关键）
    #

    payload["displayNo"] = "prwrf1000"
    payload["displayNoFrm"] = "prwrc1000"

    #
    # headers
    #

    headers = {

        "Referer":
            DAILY_URL,

        "Origin":
            "https://www.cm9.eprs.jp",

        "Content-Type":
            "application/x-www-form-urlencoded",

        "User-Agent":
            "Mozilla/5.0",
    }

    trace(
        f"open reservation: "
        f"{candidate['date']} "
        f"{candidate['mansion']} "
        f"{candidate['start']}"
    )

    r = session.post(
        RESERVE_APPLY_URL,
        data=payload,
        headers=headers,
        timeout=30,
    )

    r.encoding = "cp932"

    html = r.text

    Path("output/reserve_apply_response.html").write_text(
        html,
        encoding="utf-8"
    )

    #
    # ✅ 关键判断
    #

    if "利用規約" not in html:

        trace("未进入利用規約页面")
        trace(html[:500])

        return None

    trace("✅ 已进入利用規約页面")

    #
    # → 确认页
    #

    soup = BeautifulSoup(html, "html.parser")

    agree_payload = {}

    for tag in soup.find_all("input"):

        name = tag.get("name")

        if not name:
            continue

        agree_payload[name] = tag.get("value", "")

    # ✅ 强制同意
    agree_payload["ruleFg"] = "1"

    trace(f"agree_payload_keys={list(agree_payload.keys())}")


    r2 = session.post(
        RESERVE_AGREEMENT_URL,
        data=agree_payload,
        headers=headers,
        timeout=30,
    )

    r2.encoding = "cp932"

    confirm_html = r2.text

    Path("output/agreement_confirm.html").write_text(
        confirm_html,
        encoding="utf-8"
    )

    if "確認" not in confirm_html:

        trace("未进入確認页面")
        trace(final_html[:500])
        return None

    trace("✅ 已进入確認页面")


    #
    # → 完了页
    #

    soup = BeautifulSoup(html, "html.parser")

    rsv_payload = {}

    # ✅ 利用人数
    rsv_payload["applyNum"] = arg.apply-num

    for tag in soup.find_all("input"):

        name = tag.get("name")

        if not name:
            continue

        rsv_payload[name] = tag.get("value", "")


    trace(f"agree_payload_keys={list(rsv_payload.keys())}")

    r2 = session.post(
        RESERVE_CONFIRM_URL,
        data=rsv_payload,
        headers=headers,
        timeout=30,
    )

    r2.encoding = "cp932"

    final_html = r2.text

    Path("output/agreement_confirm.html").write_text(
        final_html,
        encoding="utf-8"
    )

    if "完了" not in final_html:

        trace("未进入完了页面")
        trace(final_html[:500])
        return None

    trace("✅ 已进入完了页面")

    return final_html


def send_mail(
    subject,
    body,
    html_content,
    to_addr
):

    smtp_host = os.getenv(
        "SMTP_HOST",
        ""
    )

    smtp_port = int(
        os.getenv(
            "SMTP_PORT",
            "587"
        )
    )

    smtp_user = os.getenv(
        "SMTP_USER",
        ""
    )

    smtp_pass = os.getenv(
        "SMTP_PASS",
        ""
    )

    if not smtp_host:

        trace("SMTP_HOST missing")
        return

    msg = MIMEMultipart("alternative")

    msg["Subject"] = subject
    msg["From"] = smtp_user
    msg["To"] = to_addr

    msg.attach(
        MIMEText(body, "plain", "utf-8")
    )

    msg.attach(
        MIMEText(html_content, "html", "utf-8")
    )

    with smtplib.SMTP_SSL(
        smtp_host,
        smtp_port
    ) as server:

        server.login(
            smtp_user,
            smtp_pass
        )

        server.send_message(msg)

    trace("mail sent")


def resolve_area(area_str):
    if area_str not in AREA_MAP:
        raise ValueError(f"Invalid area: {area_str}")
    return AREA_MAP[area_str]


def resolve_activity(activity_str):
    if activity_str not in ACTIVITY_MAP:
        raise ValueError(f"Invalid activity: {activity_str}")
    return ACTIVITY_MAP[activity_str]


def main():

    TRACE_FILE.write_text(
        "",
        encoding="utf-8"
    )

    args = parse_args()

    sd = datetime.strptime(args.start_date, '%Y-%m-%d')
    if (
        (sd.month == 12 and sd.day >= 29)
        or
        (sd.month == 1 and sd.day <= 3)
    ):
        trace("skip holiday window (12/29-1/3)")
        return


    activity = resolve_activity(args.activity)

    if int(args.period_days) <= 0:
        raise ValueError("period-days must be > 0")
    if int(args.period_days) > 31:
        raise ValueError("period-days must be <= 31")

    if int(args.date_mode) <= 0:
        raise ValueError("date-mode must be > 0")
    if int(args.date_mode) > 4:
        raise ValueError("date-mode must be <= 4")

    for w in args.weekdays.split(","):
        if int(w) < 0 or int(w) > 9 or int(w) == 8:
            raise ValueError("weekday must be in 1-9, and not 8")

    session = requests.Session()

    session.headers.update({

        "User-Agent":
            "Mozilla/5.0",

        "Referer":
            INDEX_URL,

        "Origin":
            "https://www.cm9.eprs.jp",
    })

    session.get(
        INDEX_URL,
        timeout=30,
    )

    if not login(session):

        trace(
            "login failed"
        )

        return

    area_bcd = resolve_area(args.area)

    session.cookies.set("daystart", args.start_date)
    session.cookies.set("bname", area_bcd)
    session.cookies.set("purpose", activity)

    trace(
        "登录后搜索"
    )

    results = do_search(
        session,
        args,
        activity
    )

    JSON_FILE.write_text(
        json.dumps(
            results,
            ensure_ascii=False,
            indent=2
        ),
        encoding="utf-8"
    )

    month_results = (
        filter_current_month(
            results
        )
    )

    critical = find_critical(
        month_results
    )

    trace(
        f"critical_count="
        f"{len(critical)}"
    )

    print()

    print("=" * 80)
    print("Critical 条件候选")
    print("=" * 80)

    for r in critical:

        print(
            f"{r['date']} | "
            f"{r['mansion']} | "
            f"{r['facility']} | "
            f"{r['start']}～{r['end']} | "
            f"{r['duration_hours']}h"
        )

    if not critical:
        return

    to_addr = args.mail_to

    if not to_addr:

        trace("mail_to missing")
        return

    for idx, candidate in enumerate(critical, start=1):

        html = open_reservation_agreement(
            session,
            candidate,
            args,
            activity
        )

        if not html:
            continue

        file_path = (
            OUTPUT_DIR
            / f"agreement_{idx}.html"
        )

        file_path.write_text(
            html,
            encoding="utf-8"
        )

        subject = (
            f"[Shinagawa] Reservation Ready "
            f"{candidate['mansion']} "
            f"{candidate['date']} "
            f"{candidate['start']}"
        )

        body = (
            f"候选已进入確認页面。\n\n"
            f"日期: {candidate['date']}\n"
            f"设施: {candidate['mansion']}\n"
            f"时间: {candidate['start']}～{candidate['end']}\n"
            f"时长: {candidate['duration_hours']}h\n"
        )

        send_mail(
            subject,
            body,
            html,
            to_addr,
        )

    print()

    print(
        f"total_results="
        f"{len(results)}"
    )

    print(
        f"critical_count="
        f"{len(critical)}"
    )

    print()

    print(
        f"TRACE: {TRACE_FILE}"
    )

    print(
        f"DAILY_HTML: {DAILY_HTML_FILE}"
    )

    print(
        f"JSON: {JSON_FILE}"
    )


if __name__ == "__main__":

    main()
