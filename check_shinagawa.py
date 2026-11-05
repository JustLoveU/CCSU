#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import os
import sys
import re
import json
import time
import argparse
import smtplib
import urllib.parse
from datetime import datetime, timedelta, timezone
from pathlib import Path
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart

import requests
from bs4 import BeautifulSoup
from dotenv import load_dotenv

load_dotenv()

# ==================== 設定情報 ====================
BASE_URL = "https://www.cm9.eprs.jp/shinagawa/web"
ORIGIN_URL = "https://www.cm9.eprs.jp"

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

USER_ID = os.getenv("SHINAGAWA_USER_ID", "")
PASSWORD = os.getenv("SHINAGAWA_PASSWORD", "")

SMTP_HOST = os.getenv("SMTP_HOST", "smtp.mail.yahoo.co.jp")
SMTP_PORT = int(os.getenv("SMTP_PORT", "465"))
SMTP_USER = os.getenv("SMTP_USER", "tl2019@yahoo.co.jp")
SMTP_PASS = os.getenv("SMTP_PASS", "")
MAIL_TO = os.getenv("MAIL_TO", "tl2019@yahoo.co.jp")

TARGET_DAYS = "31"
TARGET_WEEKDAYS = "3,5,6,7,9"           # 水,金,土,日,祝
TARGET_PURPOSE = "31000000_31010600"   # バドミントン
APPLY_NUM = "10"

# 対象校が存在する地区のみを定義
ALL_AREA_MAP = {
    "大崎": "1300_0",
    "荏原": "1100_0"
}

# 監視対象校（全7校）
TARGET_MANSIONS = {
    "第三日野小学校",
    "第四日野小学校",
    "日野学園",
    "荏原第六中学校",
    "小山台小学校",
    "小山小学校",
    "後地小学校"
}

AUTO_BOOK = True

OUTPUT_DIR = Path("output")
OUTPUT_DIR.mkdir(parents=True, exist_ok=True)

JST = timezone(timedelta(hours=9))


def is_maintenance_time() -> bool:
    now_jst = datetime.now(JST)
    return 0 <= now_jst.hour < 5


def get_start_date() -> str:
    return datetime.now(JST).strftime("%Y-%m-%d")


def get_current_month_prefix() -> str:
    """当月プレフィックス（例: '202611'）"""
    return datetime.now(JST).strftime("%Y%m")


def log(msg: str):
    timestamp = datetime.now(JST).strftime("%Y-%m-%d %H:%M:%S")
    formatted = f"[{timestamp}] {msg}"
    print(formatted)
    with open(OUTPUT_DIR / "execution.log", "a", encoding="utf-8") as f:
        f.write(formatted + "\n")


def send_mail(subject: str, body: str):
    if not SMTP_PASS:
        log("【警告】SMTP_PASSが未設定のため、メール送信をスキップします。")
        return

    msg = MIMEMultipart()
    msg["From"] = SMTP_USER
    msg["To"] = MAIL_TO
    msg["Subject"] = subject
    msg.attach(MIMEText(body, "plain", "utf-8"))

    try:
        log(f"メール発報中 ({SMTP_HOST}:{SMTP_PORT}) -> {MAIL_TO}")
        with smtplib.SMTP_SSL(SMTP_HOST, SMTP_PORT, timeout=15) as server:
            server.login(SMTP_USER, SMTP_PASS)
            server.send_message(msg)
        log("=> ★ メールを送信しました！")
    except Exception as e:
        log(f"【エラー】メール送信失敗: {e}")


def is_target_school(name: str) -> bool:
    if not name:
        return False
    return any(target in name for target in TARGET_MANSIONS)


def is_current_month(use_ymd: str) -> bool:
    """当月（YYYYMM）の枠であるかを判定（翌月以降は除外）"""
    cur_ym = get_current_month_prefix()
    return str(use_ymd).startswith(cur_ym)


def get_slot_priority(slot: dict) -> tuple:
    use_date = str(slot.get("useYmd", "99999999"))
    start_time = str(slot.get("sTime", "9999"))
    return (use_date, start_time)


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

        r_ajax = session.post(AJAX_URL, data=encoded, headers=headers, timeout=15)
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


def search_district_slots(session: requests.Session, area_bcd: str) -> list[dict]:
    start_date = get_start_date()
    search_payload = [
        ("date", "4"),
        ("daystart", start_date),
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

    session.post(SEARCH_URL, data=search_payload, timeout=15)

    r_daily = session.post(DAILY_URL, data={"displayNo": "prwrc2000"}, timeout=15)
    r_daily.encoding = "cp932"

    form_data = extract_form_data(r_daily.text)
    if not form_data:
        return []

    return fetch_all_slots_paged(session, form_data)


def scan_specified_districts(session: requests.Session, target_areas: dict, print_log: bool = True) -> list[dict]:
    """指定された地区（大崎・荏原）のみを走査し、当月枠のみを抽出"""
    detected_slots = []
    for area_name, area_bcd in target_areas.items():
        t0 = time.time()
        all_slots = search_district_slots(session, area_bcd)
        elapsed = (time.time() - t0) * 1000

        target_slots = []
        for slot in all_slots:
            mansion = slot.get("bcdNm", "")
            use_ymd = str(slot.get("useYmd", ""))
            # 対象校 かつ 【当月枠】 のみ
            if is_target_school(mansion) and is_current_month(use_ymd):
                slot["_area_name"] = area_name
                slot["_area_bcd"] = area_bcd
                target_slots.append(slot)

        if print_log:
            if target_slots:
                log(f"[{area_name}地区] ★ 対象校の【当月】空き枠を {len(target_slots)} 件検出！ ({elapsed:.1f}ms)")
            else:
                log(f"[{area_name}地区] 対象校の当月空きなし (全{len(all_slots)}件走査 / {elapsed:.1f}ms)")

        detected_slots.extend(target_slots)
        time.sleep(0.15)

    detected_slots.sort(key=get_slot_priority)
    return detected_slots


def login(session: requests.Session) -> bool:
    log("▶ 品川区システムへ認証ログインを実行します...")

    r = session.post(LOGIN_PAGE_URL, data={"displayNo": "pawab2000"}, timeout=15)
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

    r_auth = session.post(LOGIN_URL, data=payload, timeout=15)
    r_auth.encoding = "cp932"

    if "ログアウト" in r_auth.text:
        soup_auth = BeautifulSoup(r_auth.text, "html.parser")
        user_name_elem = soup_auth.find(id="userName") or soup_auth.find("span", class_="dropdown-item-text")
        user_name = user_name_elem.get_text(strip=True) if user_name_elem else f"ID: {USER_ID}"
        log(f"=> ★ ログイン成功！ 認証者: 『{user_name}』")
        return True
    else:
        log("=> 【ログイン失敗】認証できませんでした。")
        return False


def logout(session: requests.Session):
    try:
        log("ログアウトを実行中...")
        session.post(LOGOUT_URL, data={"displayNo": "pawab2000"}, timeout=10)
        log("=> ログアウト完了（セッション破棄）")
    except Exception as e:
        log(f"ログアウト通信エラー: {e}")


def get_confirm_page(session: requests.Session, candidate_raw: dict, area_bcd: str) -> str:
    start_date = get_start_date()
    search_payload = [
        ("date", "4"),
        ("daystart", start_date),
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

    session.post(SEARCH_URL, data=search_payload, timeout=15)
    r_daily = session.post(DAILY_URL, data={"displayNo": "prwrc2000"}, timeout=15)
    r_daily.encoding = "cp932"

    soup_daily = BeautifulSoup(r_daily.text, "html.parser")
    apply_payload = {tag.get("name"): tag.get("value", "") for tag in soup_daily.find_all("input") if tag.get("name")}

    apply_payload.update({
        "date": "4",
        "daystart": start_date,
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

    r_apply = session.post(RESERVE_APPLY_URL, data=apply_payload, headers=headers, timeout=20)
    r_apply.encoding = "cp932"
    current_html = r_apply.text

    if "利用規約" in current_html:
        soup_agree = BeautifulSoup(current_html, "html.parser")
        agree_payload = {tag.get("name"): tag.get("value", "") for tag in soup_agree.find_all("input") if tag.get("name")}
        agree_payload["ruleFg"] = "1"

        r_agree = session.post(RESERVE_AGREEMENT_URL, data=agree_payload, headers=headers, timeout=20)
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
        has_badminton = any("バド" in opt.get_text() for opt in options)
        if has_badminton or any(k in name_lower for k in ["pps", "purpose"]):
            bad_opt = next((opt for opt in options if "バド" in opt.get_text() or TARGET_PURPOSE in opt.get("value", "")), None)
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
        log(f"   ★ 選択枠: 『{target_text}』 (利用時間: {duration_min/60:.1f}時間 / コード: {target_val})")
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

    r_final = session.post(RESERVE_CONFIRM_URL, data=final_payload, headers=headers, timeout=20)
    r_final.encoding = "cp932"
    final_html = r_final.text

    if "完了" in final_html or "受付番号" in final_html or "予約番号" in final_html:
        rsv_no_match = re.search(r'(?:予約番号|受付番号)[：:\s]*([A-Za-z0-9\-]+)', final_html)
        rsv_no = rsv_no_match.group(1) if rsv_no_match else "画面参照"

        log(f"★★★ 【予約完了】 {mansion} / 枠: {target_text} / 予約番号: {rsv_no} ★★★")

        mail_subject = f"【品川区 予約完了通知】施設予約が確定しました ({mansion} {target_text})"
        mail_body = (
            f"品川区施設予約システムにて自動予約が完了しました。\n\n"
            f"■ 予約内容\n"
            f"- 予約番号: {rsv_no}\n"
            f"- 施設名: {mansion}\n"
            f"- 室場名: {facility}\n"
            f"- 利用日時: {date_str} {target_text}\n"
            f"- 利用人数: {APPLY_NUM}人\n"
            f"- 利用目的: バドミントン\n"
            f"- 確定時刻: {datetime.now(JST).strftime('%Y-%m-%d %H:%M:%S')}\n\n"
            f"詳細は品川区マイページの予約一覧をご確認ください。"
        )
        send_mail(mail_subject, mail_body)
        return True
    else:
        log(f"【警告】『{mansion}』の完了画面キーワードが確認できませんでした。")
        return False


def parse_args():
    parser = argparse.ArgumentParser()
    parser.add_argument("--area", default="", help="巡回地区（大崎 または 荏原）。未指定時は両地区を巡回")
    return parser.parse_known_args()[0]


def main():
    args = parse_args()

    # 巡回地区の設定（CLI引数で単一地区が指定されていればそれを使用、未指定なら両地区）
    if args.area and args.area in ALL_AREA_MAP:
        target_areas = {args.area: ALL_AREA_MAP[args.area]}
    else:
        target_areas = ALL_AREA_MAP

    start_date = get_start_date()
    cur_ym = get_current_month_prefix()
    log("=== 品川区学校施設 巡回ボット 起動（当月枠限定・最長枠優先版） ===")
    log(f"検索開始日: {start_date} / 対象月: {cur_ym[:4]}年{cur_ym[4:]}月 / 設定人数: {APPLY_NUM}人")
    log(f"巡回地区: {', '.join(target_areas.keys())} / 監視対象校（全{len(TARGET_MANSIONS)}校）: {', '.join(sorted(TARGET_MANSIONS))}")

    if not USER_ID or not PASSWORD:
        log("【エラー】SHINAGAWA_USER_ID または SHINAGAWA_PASSWORD が設定されていません。")
        return

    session = requests.Session()
    session.headers.update({
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36",
        "Referer": INDEX_URL,
        "Origin": ORIGIN_URL,
    })

    logged_in = False

    try:
        session.get(INDEX_URL, timeout=10)

        # 1. 未ログインで指定地区（大崎・荏原）を高速走査（当月枠のみ抽出＆地区ログ出力）
        initial_slots = scan_specified_districts(session, target_areas, print_log=True)

        # 2. 当月の対象枠がなければ、ログイン処理を一切行わずに終了（監査ログゼロ）
        if not initial_slots:
            log("=> 対象校の【当月】空き枠はありませんでした。ログインせずに巡回を終了します。")
            return

        candidate_lines = []
        log("=" * 70)
        log(f"【検知候補リスト（日時順: 全 {len(initial_slots)} 件）】")
        for rank, s in enumerate(initial_slots, 1):
            use_d = str(s.get('useYmd', ''))
            d_str = f"{use_d[:4]}-{use_d[4:6]}-{use_d[6:8]}"
            t_str = f"{s.get('sJTime2', '')}〜{s.get('eJTime2', '')}"
            fac = s.get('bcdNm', '')
            rm = s.get('icdNm', '')
            info_line = f"[{rank}位] {d_str} {t_str} | {fac} ({rm}) [{s['_area_name']}地区]"
            log(f"  {info_line}")
            candidate_lines.append(f"- {info_line}")
        log("=" * 70)

        # 3. 夜間メンテナンス判定
        if is_maintenance_time():
            now_jst = datetime.now(JST)
            target_0500 = now_jst.replace(hour=5, minute=0, second=3, microsecond=0)
            wait_seconds = (target_0500 - now_jst).total_seconds()

            log(f"【夜間メンテナンス時間帯検知 (JST {now_jst.strftime('%H:%M:%S')})】")
            log(f"=> メンテ明け (05:00:03) まで {int(wait_seconds)} 秒間待機して即時予約を狙います。")

            mail_subject = f"【速報・待機中】品川区学校施設に空き枠検知 (メンテ明け05:00に自動予約実行予定)"
            mail_body = (
                f"品川区施設予約システムにて対象校の当月空き枠を検知しました。\n"
                f"現在システム夜間メンテナンス中のため、スクリプトは 05:00:03 まで待機し、\n"
                f"メンテ明けと同時に自動予約を試みます。\n\n"
                f"■ 検出された候補一覧:\n"
                + "\n".join(candidate_lines) + "\n\n"
                f"検知時刻: {now_jst.strftime('%Y-%m-%d %H:%M:%S')}"
            )
            send_mail(mail_subject, mail_body)

            if wait_seconds > 0:
                time.sleep(wait_seconds)

            log("=> 05:00 を迎えました！ メンテ明け即時予約処理を開始します。")

        # 4. 当月枠が存在する場合のみログイン実行
        if not login(session):
            return
        logged_in = True

        # 5. 【動的再巡回予約ループ】
        MAX_BOOKINGS = 5
        booked_count = 0
        failed_slot_keys = set()

        for loop_idx in range(1, MAX_BOOKINGS + 1):
            log(f"\n--- [予約ラウンド {loop_idx}/{MAX_BOOKINGS}] 最新の空き枠状況を再巡回中... ---")
            current_slots = scan_specified_districts(session, target_areas, print_log=False)

            valid_slots = []
            for s in current_slots:
                key = (str(s.get("useYmd")), str(s.get("bcd")), str(s.get("icd")), str(s.get("sTime")))
                if key not in failed_slot_keys:
                    valid_slots.append(s)

            if not valid_slots:
                log("=> 対象校の当月空き枠がすべて埋まった（または取り切った）ため、予約ループを終了します。")
                break

            target_slot = valid_slots[0]
            slot_key = (str(target_slot.get("useYmd")), str(target_slot.get("bcd")), str(target_slot.get("icd")), str(target_slot.get("sTime")))
            area_bcd = target_slot["_area_bcd"]

            success = execute_single_booking(session, target_slot, area_bcd)
            if success:
                booked_count += 1
                log("=> 予約完了！ 最新台帳を確認するため、1.5秒後に次ラウンドの再巡回へ進みます。")
                time.sleep(1.5)
            else:
                log("=> この枠の確定に失敗したため、次回巡回から除外して別枠を狙います。")
                failed_slot_keys.add(slot_key)
                time.sleep(1.0)

        log(f"\n=== 予約処理完了: 合計 {booked_count} 件の予約を確定しました ===")

    finally:
        if logged_in:
            logout(session)
        log("=== 巡回処理 終了 ===")


if __name__ == "__main__":
    main()
