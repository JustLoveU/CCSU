#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import os
import sys
import re
import json
import time
import calendar
import urllib.parse
from datetime import datetime, timedelta, timezone
from pathlib import Path

import requests
from requests.adapters import HTTPAdapter
from bs4 import BeautifulSoup
from dotenv import load_dotenv

load_dotenv()

# ==================== 環境変数・設定読込 ====================
def parse_csv_env(key: str) -> list[str]:
    val = os.getenv(key, "")
    return [x.strip() for x in val.split(",") if x.strip()]

BASE_URL = os.getenv("MINATO_BASE_URL", "").rstrip("/")
parsed_base = urllib.parse.urlparse(BASE_URL)
ORIGIN_URL = f"{parsed_base.scheme}://{parsed_base.netloc}" if BASE_URL else ""

INDEX_URL = f"{BASE_URL}/index.jsp"
LOGIN_PAGE_URL = f"{BASE_URL}/rsvWTransUserLoginAction.do"
LOGIN_URL = f"{BASE_URL}/rsvWUserAttestationLoginAction.do"
LOGOUT_URL = f"{BASE_URL}/rsvWTransUserAttestationEndAction.do"

SEARCH_URL = f"{BASE_URL}/rsvWOpeInstSrchVacantAction.do"
DAILY_URL = f"{BASE_URL}/rsvWOpeUnreservedDailyAction.do"
AJAX_URL = f"{BASE_URL}/rsvWOpeUnreservedSearchAjaxAction.do"

RESERVE_APPLY_URL = f"{BASE_URL}/rsvWOpeReservedApplyAction.do"
RESERVE_AGREEMENT_URL = f"{BASE_URL}/rsvWInstUseruleRsvApplyAction.do"
RESERVE_CONFIRM_URL = f"{BASE_URL}/rsvWInstRsvApplyAction.do"

USER_ID = os.getenv("RSV_USER_ID", "")
PASSWORD = os.getenv("RSV_PASSWORD", "")

# Slack Incoming Webhook 設定
SLACK_WEBHOOK_URL = os.getenv("TLSLACK_WEBHOOK_URL", "")

# 学校リスト（Secrets / 環境変数から読込）
ALL_SCHOOLS = set(parse_csv_env("MINATO_ALL_SCHOOLS"))
EXCLUDE_SCHOOLS = set(parse_csv_env("MINATO_EXCLUDE_SCHOOLS"))
PRIORITY_ORDER = parse_csv_env("MINATO_PRIORITY_SCHOOLS")

START_OFFSET_DAYS = 3                   # 検索開始日: 3日先から
TARGET_DAYS = "31"                      # 1回あたりの検索期間上限
TARGET_WEEKDAYS = "3,5,6,7,9"           # 水,金,土,日,祝
TARGET_PURPOSE = "2010_2010040"         # バドミントン
APPLY_NUM = "10"

AREA_MAP = {
    "麻布": "1000_0",
    "赤坂": "2000_0",
    "高輪": "3000_0",
    "芝浦港南": "4000_0",
    "芝": "5000_0"
}

AUTO_BOOK = True

OUTPUT_DIR = Path("output")
OUTPUT_DIR.mkdir(parents=True, exist_ok=True)

JST = timezone(timedelta(hours=9))


def is_maintenance_time() -> bool:
    """JST 00:00 〜 04:59 のシステムメンテナンス時間帯か判定"""
    return 0 <= datetime.now(JST).hour < 5


def get_search_windows() -> tuple[list[tuple[str, str]], str, str]:
    now_jst = datetime.now(JST)
    start_dt = now_jst.date() + timedelta(days=START_OFFSET_DAYS)

    if now_jst.month == 12:
        next_year, next_month = now_jst.year + 1, 1
    else:
        next_year, next_month = now_jst.year, now_jst.month + 1
    last_day = calendar.monthrange(next_year, next_month)[1]
    max_dt = datetime(next_year, next_month, last_day).date()

    start_ymd = start_dt.strftime("%Y%m%d")
    max_ymd = max_dt.strftime("%Y%m%d")

    if start_dt > max_dt:
        return [], start_ymd, max_ymd

    windows = []
    w1_start = start_dt
    w1_end = w1_start + timedelta(days=30)
    windows.append((w1_start.strftime("%Y-%m-%d"), TARGET_DAYS))

    if w1_end < max_dt:
        w2_start = w1_end + timedelta(days=1)
        windows.append((w2_start.strftime("%Y-%m-%d"), TARGET_DAYS))

    return windows, start_ymd, max_ymd


def log(msg: str):
    timestamp = datetime.now(JST).strftime("%Y-%m-%d %H:%M:%S")
    formatted = f"[{timestamp}] {msg}"
    print(formatted)
    with open(OUTPUT_DIR / "execution.log", "a", encoding="utf-8") as f:
        f.write(formatted + "\n")


def send_slack(text: str):
    """Slack Incoming Webhook 経由で通知を送信"""
    if not SLACK_WEBHOOK_URL:
        log("【警告】SLACK_WEBHOOK_URL が未設定のため通知をスキップします。")
        return

    try:
        res = requests.post(SLACK_WEBHOOK_URL, json={"text": text}, timeout=10)
        if res.status_code == 200:
            log("=> ★ Slack通知を送信しました！")
        else:
            log(f"【エラー】Slack送信失敗 (HTTP {res.status_code}): {res.text}")
    except Exception as e:
        log(f"【エラー】Slack通信例外: {e}")


def is_target_school(name: str) -> bool:
    clean_name = name.strip()
    if not clean_name:
        return False
    for ex in EXCLUDE_SCHOOLS:
        if clean_name == ex or ex in clean_name:
            return False
    for school in ALL_SCHOOLS:
        if clean_name == school or school in clean_name:
            return True
    return False


def get_school_priority(slot: dict) -> tuple:
    mansion = slot.get("bcdNm", "").strip()
    priority_score = 999
    for idx, p_name in enumerate(PRIORITY_ORDER):
        if mansion == p_name:
            priority_score = idx
            break
    if priority_score == 999:
        for idx, p_name in sorted(enumerate(PRIORITY_ORDER), key=lambda x: -len(x[1])):
            if p_name in mansion:
                priority_score = idx
                break

    use_date = str(slot.get("useYmd", "99999999"))
    start_time = str(slot.get("sTime", "9999"))
    return (priority_score, use_date, start_time)


def sort_time_options_by_duration(options: list[tuple[str, str]]) -> list[tuple[str, str, int]]:
    parsed = []
    for val, text in options:
        times = re.findall(r'(\d{1,2}):(\d{2})', text)
        if len(times) >= 2:
            sh, sm = int(times[0][0]), int(times[0][1])
            eh, em = int(times[1][0]), int(times[1][1])
            duration = (eh * 60 + em) - (sh * 60 + sm)
            parsed.append((val, text, duration, sh * 60 + sm))
        else:
            parsed.append((val, text, 0, 9999))

    parsed.sort(key=lambda x: (-x[2], x[3]))
    return [(item[0], item[1], item[2]) for item in parsed]


def extract_form_data(html: str) -> dict:
    m = re.search(r'var formData = (\{.*?\});', html, re.S)
    if not m:
        return None
    return json.loads(m.group(1))


def fetch_all_slots_paged(session: requests.Session, form_data: dict, limit_max: int = 100) -> list[dict]:
    results = []
    seen_keys = set()
    offset = 0

    headers = {
        "X-Requested-With": "XMLHttpRequest",
        "Referer": DAILY_URL,
        "Origin": ORIGIN_URL,
        "Accept": "application/json, text/javascript, */*; q=0.01",
        "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
    }

    while True:
        form_data["offset"] = offset
        encoded = urllib.parse.urlencode(form_data, doseq=True)

        r_ajax = session.post(AJAX_URL, data=encoded, headers=headers, timeout=10)
        r_ajax.encoding = "cp932"

        try:
            data = r_ajax.json()
        except Exception:
            break

        rows = data.get("results", [])
        if not rows:
            break

        for item in rows:
            unique_key = (
                str(item.get("useYmd", "")),
                str(item.get("bcd", "")),
                str(item.get("icd", "")),
                str(item.get("sTime", ""))
            )
            if unique_key not in seen_keys:
                seen_keys.add(unique_key)
                results.append(item)

        next_offset = data.get("next")
        if not next_offset or int(next_offset) <= offset or int(next_offset) >= limit_max:
            break
        offset = int(next_offset)

    return results


def search_district_slots(session: requests.Session, area_bcd: str, daystart: str, days: str) -> list[dict]:
    search_payload = [
        ("date", "5"),
        ("daystart", daystart),
        ("days", days),
        ("dayofweekClearFlg", "0"),
        ("timezoneClearFlg", "0"),
        ("selectAreaBcd", area_bcd),
        ("selectIcd", ""),
        ("selectPpsClPpscd", TARGET_PURPOSE),
        ("displayNo", "pawab2000"),
        ("displayNoFrm", "pawab2000"),
    ]
    for w in TARGET_WEEKDAYS.split(","):
        search_payload.append(("dayofweek", w))
    search_payload.append(("timezone", "9"))

    session.post(SEARCH_URL, data=search_payload, timeout=10)

    r_daily = session.post(DAILY_URL, data={"displayNo": "prwrc2000"}, timeout=10)
    r_daily.encoding = "cp932"

    form_data = extract_form_data(r_daily.text)
    if not form_data:
        return []

    return fetch_all_slots_paged(session, form_data)


def scan_all_districts(session: requests.Session, windows: list[tuple[str, str]], start_ymd: str, max_ymd: str, print_log: bool = True) -> list[dict]:
    detected_slots = []
    seen_slot_keys = set()

    for area_name, area_bcd in AREA_MAP.items():
        t0 = time.time()
        total_scanned = 0
        area_target_slots = []

        for daystart, days in windows:
            all_slots = search_district_slots(session, area_bcd, daystart, days)
            total_scanned += len(all_slots)

            for slot in all_slots:
                use_ymd = str(slot.get("useYmd", ""))
                if not use_ymd or use_ymd < start_ymd or use_ymd > max_ymd:
                    continue

                mansion = slot.get("bcdNm", "")
                if not is_target_school(mansion):
                    continue

                slot_key = (use_ymd, str(slot.get("bcd", "")), str(slot.get("icd", "")), str(slot.get("sTime", "")))
                if slot_key in seen_slot_keys:
                    continue
                seen_slot_keys.add(slot_key)

                slot["_area_name"] = area_name
                slot["_area_bcd"] = area_bcd
                slot["_search_daystart"] = daystart
                area_target_slots.append(slot)

        elapsed = (time.time() - t0) * 1000

        if print_log:
            if area_target_slots:
                log(f"[{area_name}地区] ★ 対象学校の空き枠を {len(area_target_slots)} 件検出！ ({elapsed:.1f}ms)")
            else:
                log(f"[{area_name}地区] 対象学校の空きなし (全{total_scanned}件走査 / {elapsed:.1f}ms)")

        detected_slots.extend(area_target_slots)

    detected_slots.sort(key=get_school_priority)
    return detected_slots


def login(session: requests.Session) -> bool:
    log("▶ 認証ログインを実行します...")

    r = session.post(LOGIN_PAGE_URL, data={"displayNo": "pawab2000"}, timeout=10)
    r.encoding = "cp932"
    soup = BeautifulSoup(r.text, "html.parser")

    jkey_tag = soup.find("input", {"name": "loginJKey"})
    login_jkey = jkey_tag.get("value", "") if jkey_tag else ""

    payload = [
        ("userId", USER_ID),
        ("password", PASSWORD),
        ("fcflg", ""),
        ("displayNo", "pawab2100"),
        ("loginJKey", login_jkey),
    ]
    for ch in PASSWORD:
        payload.append(("loginCharPass", ch))

    r_auth = session.post(LOGIN_URL, data=payload, timeout=10)
    r_auth.encoding = "cp932"

    if "ログアウト" in r_auth.text:
        log("=> ★ ログイン成功！")
        return True
    else:
        log("=> 【ログイン失敗】認証できませんでした。")
        return False


def logout(session: requests.Session):
    try:
        session.post(LOGOUT_URL, data={"displayNo": "pawab2000"}, timeout=5)
        log("=> ログアウト完了（セッション破棄）")
    except Exception as e:
        log(f"ログアウト通信エラー: {e}")


def get_confirm_page(session: requests.Session, candidate_raw: dict, area_bcd: str) -> str:
    slot_daystart = candidate_raw.get("_search_daystart") or (datetime.now(JST).date() + timedelta(days=START_OFFSET_DAYS)).strftime("%Y-%m-%d")

    search_payload = [
        ("date", "5"),
        ("daystart", slot_daystart),
        ("days", TARGET_DAYS),
        ("dayofweekClearFlg", "0"),
        ("timezoneClearFlg", "0"),
        ("selectAreaBcd", area_bcd),
        ("selectIcd", ""),
        ("selectPpsClPpscd", TARGET_PURPOSE),
        ("displayNo", "pawab2000"),
        ("displayNoFrm", "pawab2000"),
    ]
    for w in TARGET_WEEKDAYS.split(","):
        search_payload.append(("dayofweek", w))
    search_payload.append(("timezone", "9"))

    session.post(SEARCH_URL, data=search_payload, timeout=10)
    r_daily = session.post(DAILY_URL, data={"displayNo": "prwrc2000"}, timeout=10)
    r_daily.encoding = "cp932"

    soup_daily = BeautifulSoup(r_daily.text, "html.parser")
    apply_payload = {tag.get("name"): tag.get("value", "") for tag in soup_daily.find_all("input") if tag.get("name")}

    apply_payload.update({
        "date": "5",
        "daystart": slot_daystart,
        "days": TARGET_DAYS,
        "dayofweekClearFlg": "0",
        "timezoneClearFlg": "0",
        "selectAreaBcd": area_bcd,
        "selectIcd": "",
        "selectPpsClPpscd": TARGET_PURPOSE,
        "selectUseYmd": str(candidate_raw.get("useYmd", "")),
        "selectBldCd": str(candidate_raw.get("bcd", "")),
        "selectInstCd": str(candidate_raw.get("icd", "")),
        "selectPpsCd": str(candidate_raw.get("ppsCd", "")),
        "selectPpsClsCd": str(candidate_raw.get("ppsClsCd", "")),
        "selectStimeZoneNo": str(candidate_raw.get("tZoneNo", "")),
        "selectEtimeZoneNo": str(candidate_raw.get("tZoneNo", "")),
        "selectStime": str(candidate_raw.get("sTime", "")),
        "selectEtime": str(candidate_raw.get("eTime", "")),
        "selectApplyNum": APPLY_NUM,
        "availableTZoneNo": str(candidate_raw.get("tZoneNo", "")),
        "availableSTime": str(candidate_raw.get("sTime", "")),
        "availableETime": str(candidate_raw.get("eTime", "")),
        "displayNo": "prwrf1000",
        "displayNoFrm": "prwrc1000"
    })

    headers = {
        "Referer": DAILY_URL,
        "Origin": ORIGIN_URL,
        "Content-Type": "application/x-www-form-urlencoded",
        "User-Agent": "Mozilla/5.0"
    }

    r_apply = session.post(RESERVE_APPLY_URL, data=apply_payload, headers=headers, timeout=12)
    r_apply.encoding = "cp932"
    current_html = r_apply.text

    if "利用規約" in current_html:
        soup_agree = BeautifulSoup(current_html, "html.parser")
        agree_payload = {tag.get("name"): tag.get("value", "") for tag in soup_agree.find_all("input") if tag.get("name")}
        agree_payload["ruleFg"] = "1"

        r_agree = session.post(RESERVE_AGREEMENT_URL, data=agree_payload, headers=headers, timeout=12)
        r_agree.encoding = "cp932"
        current_html = r_agree.text

    return current_html


def build_final_payload(soup_confirm: BeautifulSoup, target_time_val: str, target_time_text: str) -> dict:
    form = soup_confirm.find("form")
    final_payload = {}
    if not form:
        return final_payload

    for tag in form.find_all("input"):
        name = tag.get("name")
        if not name:
            continue
        itype = tag.get("type", "text").lower()
        if itype in ["checkbox", "radio"]:
            if tag.has_attr("checked"):
                final_payload[name] = tag.get("value", "")
        elif itype not in ["button", "submit", "reset", "image"]:
            final_payload[name] = tag.get("value", "")

    for sel in form.find_all("select"):
        name = sel.get("name")
        if not name:
            continue
        options = sel.find_all("option")
        if not options:
            continue

        name_lower = name.lower()
        has_badminton = any("バドミントン" in opt.get_text() for opt in options)
        if has_badminton or any(k in name_lower for k in ["pps", "purpose"]):
            bad_opt = next((opt for opt in options if "バドミントン" in opt.get_text() or TARGET_PURPOSE in opt.get("value", "")), None)
            final_payload[name] = bad_opt.get("value", "") if bad_opt else TARGET_PURPOSE
            continue

        has_time = any(re.search(r'\d{1,2}[:時]\d{2}', opt.get_text()) for opt in options)
        if has_time or any(k in name_lower for k in ["stime", "etime", "tzone", "timezone"]):
            matched_opt = next((opt for opt in options if opt.get("value") == target_time_val or target_time_text in opt.get_text()), None)
            final_payload[name] = matched_opt.get("value", "") if matched_opt else target_time_val
            continue

        sel_opt = sel.find("option", selected=True) or options[0]
        final_payload[name] = sel_opt.get("value", "")

    final_payload.update({
        "applyNum": APPLY_NUM,
        "ninzu": APPLY_NUM,
        "purpose": TARGET_PURPOSE,
        "displayNo": "prwea1000",
        "selectRsvDetailNo": "0",
    })
    if target_time_val:
        final_payload["stimeZoneNo"] = str(target_time_val)
        final_payload["etimeZoneNo"] = str(target_time_val)

    return final_payload


def execute_single_booking(session: requests.Session, candidate_raw: dict, area_bcd: str) -> bool:
    mansion = candidate_raw.get("bcdNm", "")
    facility = candidate_raw.get("icdNm", "")
    use_date = str(candidate_raw.get("useYmd", ""))
    date_str = f"{use_date[:4]}-{use_date[4:6]}-{use_date[6:8]}"
    time_str = f"{candidate_raw.get('sJTime2', '')}〜{candidate_raw.get('eJTime2', '')}"
    slot_info = f"{date_str} {time_str} | {mansion} ({facility})"

    log(f"★ 予約手続きを開始: {slot_info}")

    if not AUTO_BOOK:
        log("【安全確認モード】AUTO_BOOK=False のため予約確定POSTはスキップします。")
        return False

    confirm_html = get_confirm_page(session, candidate_raw, area_bcd)
    if "確認" not in confirm_html and "予約" not in confirm_html:
        log(f"【エラー】『{mansion}』の予約確認画面へ進めませんでした。")
        return False

    soup_confirm = BeautifulSoup(confirm_html, "html.parser")

    raw_time_options = []
    for sel in soup_confirm.find_all("select"):
        opts = sel.find_all("option")
        valid_opts = [
            (opt.get("value", ""), opt.get_text(strip=True))
            for opt in opts
            if opt.get("value") and opt.get("value") != "0" and "選択" not in opt.get_text()
        ]
        if any(re.search(r'\d{1,2}[:時]\d{2}', t) for v, t in valid_opts):
            raw_time_options = valid_opts
            break

    if raw_time_options:
        sorted_opts = sort_time_options_by_duration(raw_time_options)
        target_val, target_text, duration_min = sorted_opts[0]
        log(f"▶ プルダウン選択肢（全{len(sorted_opts)}個）から【最長時間枠】を選択:")
        log(f"   ★ 選択枠: 『{target_text}』 (利用時間: {duration_min/60:.1f}時間)")
    else:
        target_val = str(candidate_raw.get("tZoneNo", ""))
        target_text = time_str

    final_payload = build_final_payload(soup_confirm, target_val, target_text)

    headers = {
        "Referer": DAILY_URL,
        "Origin": ORIGIN_URL,
        "Content-Type": "application/x-www-form-urlencoded",
        "User-Agent": "Mozilla/5.0"
    }

    r_final = session.post(RESERVE_CONFIRM_URL, data=final_payload, headers=headers, timeout=12)
    r_final.encoding = "cp932"
    final_html = r_final.text

    if "完了" in final_html or "受付番号" in final_html or "予約番号" in final_html:
        rsv_no_match = re.search(r'(?:予約番号|受付番号)[：:\s]*([A-Za-z0-9\-]+)', final_html)
        rsv_no = rsv_no_match.group(1) if rsv_no_match else "画面参照"

        log(f"★★★ 【予約完了】 {mansion} / 枠: {target_text} / 予約番号: {rsv_no} ★★★")

        slack_msg = (
            f"🎉 *【港区 予約完了】*\n"
            f"• *施設*: {mansion} ({facility})\n"
            f"• *日時*: {date_str} {target_text}\n"
            f"• *人数*: {APPLY_NUM}人 (バドミントン)\n"
            f"• *予約番号*: `{rsv_no}`\n"
            f"• *確定時刻*: {datetime.now(JST).strftime('%H:%M:%S')}"
        )
        send_slack(slack_msg)
        return True
    else:
        log(f"【警告】『{mansion}』の完了画面キーワードが確認できませんでした。")
        return False


def main():
    if not BASE_URL or not ALL_SCHOOLS:
        log("【エラー】必須環境変数（MINATO_BASE_URL / MINATO_ALL_SCHOOLS）が未設定です。")
        sys.exit(1)

    windows, start_ymd, max_ymd = get_search_windows()
    start_disp = f"{start_ymd[:4]}-{start_ymd[4:6]}-{start_ymd[6:8]}"
    max_disp = f"{max_ymd[:4]}-{max_ymd[4:6]}-{max_ymd[6:8]}"

    log("=== 港区学校施設 高速巡回ボット 起動 ===")
    log(f"対象期間: {start_disp} 〜 {max_disp} ({len(windows)}区間) / 対象校: {len(ALL_SCHOOLS)}校 / 除外: {len(EXCLUDE_SCHOOLS)}校")

    if not windows:
        return

    session = requests.Session()
    adapter = HTTPAdapter(pool_connections=10, pool_maxsize=10, max_retries=1)
    session.mount("https://", adapter)
    session.headers.update({
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36",
        "Referer": INDEX_URL,
        "Origin": ORIGIN_URL,
    })

    logged_in = False

    try:
        session.get(INDEX_URL, timeout=8)

        initial_slots = scan_all_districts(session, windows, start_ymd, max_ymd, print_log=True)

        if not initial_slots:
            log("=> 全地区で対象学校の空き枠はありませんでした。ログインせずに終了します。")
            return

        candidate_lines = []
        log("=" * 60)
        log(f"【検知候補リスト（優先順位順: 全 {len(initial_slots)} 件）】")
        for rank, s in enumerate(initial_slots, 1):
            use_d = str(s.get('useYmd', ''))
            d_str = f"{use_d[:4]}-{use_d[4:6]}-{use_d[6:8]}"
            t_str = f"{s.get('sJTime2', '')}〜{s.get('eJTime2', '')}"
            fac = s.get('bcdNm', '')
            rm = s.get('icdNm', '')
            info_line = f"[{rank}位] {d_str} {t_str} | {fac} ({rm})"
            log(f"  {info_line}")
            candidate_lines.append(f"• {info_line}")
        log("=" * 60)

        if is_maintenance_time():
            log("【夜間メンテナンス時間帯 (JST 00:00〜05:00)】ログインせずSlack速報のみ送信して終了します。")
            slack_msg = (
                f"⚡ *【港区 空き枠検知（夜間メンテ中）】*\n"
                f"05:01の定期便で自動予約を試みます。\n\n"
                + "\n".join(candidate_lines)
            )
            send_slack(slack_msg)
            return

        if not login(session):
            return
        logged_in = True

        MAX_BOOKINGS = 5
        booked_count = 0
        failed_slot_keys = set()

        for loop_idx in range(1, MAX_BOOKINGS + 1):
            log(f"\n--- [予約ラウンド {loop_idx}/{MAX_BOOKINGS}] 再巡回中... ---")
            current_slots = scan_all_districts(session, windows, start_ymd, max_ymd, print_log=False)

            valid_slots = []
            for s in current_slots:
                key = (str(s.get("useYmd")), str(s.get("bcd")), str(s.get("icd")), str(s.get("sTime")))
                if key not in failed_slot_keys:
                    valid_slots.append(s)

            if not valid_slots:
                log("=> 対象枠がすべて完了したためループを終了します。")
                break

            target_slot = valid_slots[0]
            slot_key = (str(target_slot.get("useYmd")), str(target_slot.get("bcd")), str(target_slot.get("icd")), str(target_slot.get("sTime")))
            area_bcd = target_slot["_area_bcd"]

            success = execute_single_booking(session, target_slot, area_bcd)
            if success:
                booked_count += 1
                time.sleep(0.6)
            else:
                failed_slot_keys.add(slot_key)
                time.sleep(0.5)

        log(f"\n=== 予約処理完了: 合計 {booked_count} 件確定 ===")

    finally:
        if logged_in:
            logout(session)
        log("=== 巡回処理 終了 ===")


if __name__ == "__main__":
    main()
