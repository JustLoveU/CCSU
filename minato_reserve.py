#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import os
import sys
import re
import json
import time
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
BASE_URL = "https://web101.rsv.ws-scs.jp/web"
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

# 認証情報
USER_ID = os.getenv("RSV_USER_ID", "60064531")
PASSWORD = os.getenv("RSV_PASSWORD", "")

# メール設定
SMTP_HOST = "smtp.mail.yahoo.co.jp"
SMTP_PORT = 465
SMTP_USER = "tl2019@yahoo.co.jp"
SMTP_PASS = os.getenv("SMTP_PASS", "")
MAIL_TO = "tl2019@yahoo.co.jp"

# 検索条件
TARGET_DATE = (datetime.now() + timedelta(days=6)).strftime("%Y-%m-%d")
TARGET_DAYS = "31"
TARGET_WEEKDAYS = "3,5,6,7,9"           # 水,金,土,日,祝
TARGET_PURPOSE = "2010_2010040"         # バドミントン
APPLY_NUM = "10"                        # 利用人数: 10人

# 港区 5地区コード
AREA_MAP = {
    "麻布": "1000_0",
    "赤坂": "2000_0",
    "高輪": "3000_0",
    "芝浦港南": "4000_0",
    "芝": "5000_0"
}

# ==================== 学校ホワイトリスト & 優先度設定 ====================
ALL_SCHOOLS = {
    # 麻布地区
    "麻布小学校", "南山小学校", "本村小学校", "六本木中学校", "高陵中学校",
    # 赤坂地区
    "赤坂学園赤坂小学校", "青山小学校", "青南小学校", "赤坂学園赤坂中学校", "青山中学校",
    # 高輪地区
    "高輪台小学校", "白金小学校", "三田中学校", "高松中学校", "白金の丘学園", "御田小学校（仮校舎）",
    # 芝浦港南地区
    "芝浦小学校", "芝浜小学校", "港南小学校", "港南中学校", "お台場学園港陽小・中学校",
    # 芝地区
    "御成門学園御成門小学校", "芝小学校", "赤羽小学校", "御成門学園御成門中学校"
}

# 除外校
EXCLUDE_SCHOOLS = {"笄小学校", "東町小学校"}

# 優先順位（インデックス順: 0が最優先）
PRIORITY_ORDER = [
    "高松中学校",
    "御成門学園御成門中学校",
    "芝浜小学校",
    "三田中学校"
]

AUTO_BOOK = True

OUTPUT_DIR = Path("output")
OUTPUT_DIR.mkdir(parents=True, exist_ok=True)
# ======================================================================

JST = timezone(timedelta(hours=9))


def is_maintenance_time() -> bool:
    """JST 00:00 〜 04:59 のシステムメンテナンス時間帯か判定"""
    now_jst = datetime.now(JST)
    return 0 <= now_jst.hour < 5


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
    for ex in EXCLUDE_SCHOOLS:
        if ex in name:
            return False
    for school in ALL_SCHOOLS:
        if school in name:
            return True
    return False


def get_school_priority(slot: dict) -> tuple:
    mansion = slot.get("bcdNm", "")
    priority_score = 999
    for idx, p_name in enumerate(PRIORITY_ORDER):
        if p_name in mansion or (p_name == "御成門学園御成門中学校" and "御成門中" in mansion):
            priority_score = idx
            break

    use_date = str(slot.get("useYmd", "99999999"))
    start_time = str(slot.get("sTime", "9999"))
    return (priority_score, use_date, start_time)


def sort_time_options_by_duration(options: list[tuple[str, str]]) -> list[tuple[str, str, int]]:
    """
    プルダウンの各選択肢から利用可能時間（分）を計算し、
    一番長い時間帯（降順）かつ早い開始時刻（昇順）でソート
    """
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

    # duration 降順、start_time 昇順
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
        "Origin": BASE_URL,
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
    """指定地区の空き枠を全件取得（ログイン有無を問わずセッション状態を維持）"""
    search_payload = [
        ("date", "5"),
        ("daystart", TARGET_DATE),
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

    session.post(SEARCH_URL, data=search_payload, timeout=15)

    r_daily = session.post(DAILY_URL, data={"displayNo": "prwrc2000"}, timeout=15)
    r_daily.encoding = "cp932"

    form_data = extract_form_data(r_daily.text)
    if not form_data:
        return []

    return fetch_all_slots_paged(session, form_data)


def scan_all_districts(session: requests.Session) -> list[dict]:
    """全5地区を走査し、対象学校の空き枠を優先度順に並べて返却"""
    detected_slots = []
    for area_name, area_bcd in AREA_MAP.items():
        all_slots = search_district_slots(session, area_bcd)
        for slot in all_slots:
            mansion = slot.get("bcdNm", "")
            if is_target_school(mansion):
                slot["_area_name"] = area_name
                slot["_area_bcd"] = area_bcd
                detected_slots.append(slot)
        time.sleep(0.15)

    detected_slots.sort(key=get_school_priority)
    return detected_slots


def login(session: requests.Session) -> bool:
    log("▶ 認証ログインを実行します...")

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
    """予約申込画面（および利用規約同意）を経て予約確認HTMLを取得"""
    search_payload = [
        ("date", "5"),
        ("daystart", TARGET_DATE),
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

    session.post(SEARCH_URL, data=search_payload, timeout=15)
    r_daily = session.post(DAILY_URL, data={"displayNo": "prwrc2000"}, timeout=15)
    r_daily.encoding = "cp932"

    soup_daily = BeautifulSoup(r_daily.text, "html.parser")
    apply_payload = {tag.get("name"): tag.get("value", "") for tag in soup_daily.find_all("input") if tag.get("name")}

    apply_payload.update({
        "date": "5",
        "daystart": TARGET_DATE,
        "days": TARGET_DAYS,
        "dayofweekClearFlg": "0",
        "timezoneClearFlg": "0",
        "selectAreaBcd": area_bcd,
        "selectIcd", "",
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
        "Origin": BASE_URL,
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
    """確認画面HTMLのinputおよびselect（最長時間帯・目的）を正確に構築"""
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
        # 目的プルダウン（バドミントン）の選択
        has_badminton = any("バドミントン" in opt.get_text() for opt in options)
        if has_badminton or any(k in name_lower for k in ["pps", "purpose"]):
            bad_opt = next((opt for opt in options if "バドミントン" in opt.get_text() or TARGET_PURPOSE in opt.get("value", "")), None)
            final_payload[name] = bad_opt.get("value", "") if bad_opt else TARGET_PURPOSE
            continue

        # 時間帯プルダウンの選択（最長枠）
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
    """プルダウン内で一番長い時間帯枠を最優先に選定し、1枠の確定処理を実行"""
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

    # 時間帯プルダウンの検出と利用時間ソート
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

    # プルダウンが存在すれば最長枠を優先ソート、無ければ元データ枠を使用
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
        "Origin": BASE_URL,
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

        mail_subject = f"【予約完了通知】港区学校施設予約が確定しました ({mansion} {target_text})"
        mail_body = (
            f"港区施設予約システムにて自動予約が完了しました。\n\n"
            f"■ 予約内容\n"
            f"- 予約番号: {rsv_no}\n"
            f"- 施設名: {mansion}\n"
            f"- 室場名: {facility}\n"
            f"- 利用日時: {date_str} {target_text}\n"
            f"- 利用人数: {APPLY_NUM}人\n"
            f"- 利用目的: バドミントン\n"
            f"- 確定時刻: {datetime.now(JST).strftime('%Y-%m-%d %H:%M:%S')}\n\n"
            f"詳細はマイページの予約一覧をご確認ください。"
        )
        send_mail(mail_subject, mail_body)
        return True
    else:
        log(f"【警告】『{mansion}』の完了画面キーワードが確認できませんでした。")
        return False


def main():
    log("=== 港区学校施設 巡回ボット 起動（最長枠優先・再巡回ループ版） ===")
    log(f"検索開始日: {TARGET_DATE} / 期間: {TARGET_DAYS}日間 / 設定人数: {APPLY_NUM}人")

    session = requests.Session()
    session.headers.update({
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36",
        "Referer": INDEX_URL,
        "Origin": BASE_URL,
    })

    logged_in = False

    try:
        session.get(INDEX_URL, timeout=10)

        # 1. まず未ログインのまま全地区を高速走査（平常時ログゼロ）
        initial_slots = scan_all_districts(session)

        # 2. 空き枠が1件もなければ、ログイン処理を一切行わずに終了
        if not initial_slots:
            log("=> 全地区で対象学校の空き枠はありませんでした。ログインせずに巡回を終了します。")
            return

        candidate_lines = []
        log("=" * 70)
        log(f"【検知候補リスト（優先順位順: 全 {len(initial_slots)} 件）】")
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

        # 3. 夜間メンテナンス時間帯（00:00〜05:00）の判定と待機
        if is_maintenance_time():
            now_jst = datetime.now(JST)
            target_0500 = now_jst.replace(hour=5, minute=0, second=3, microsecond=0)
            wait_seconds = (target_0500 - now_jst).total_seconds()

            log(f"【夜間メンテナンス時間帯検知 (JST {now_jst.strftime('%H:%M:%S')})】")
            log(f"=> メンテ明け (05:00:03) まで {int(wait_seconds)} 秒間待機して即時予約を狙います。")

            mail_subject = f"【速報・待機中】夜間メンテ明け(05:00)に自動予約を実行します ({len(initial_slots)}件)"
            mail_body = (
                f"港区施設予約システムにて対象学校の空き枠を検知しました。\n"
                f"現在システムメンテナンス中のため、スクリプトは 05:00:03 まで待機し、\n"
                f"メンテ明けと同時に自動予約を試みます。\n\n"
                f"■ 検出された候補一覧（優先順位順）:\n"
                + "\n".join(candidate_lines) + "\n\n"
                f"検知時刻: {now_jst.strftime('%Y-%m-%d %H:%M:%S')}"
            )
            send_mail(mail_subject, mail_body)

            if wait_seconds > 0:
                time.sleep(wait_seconds)

            log("=> 05:00 を迎えました！ メンテ明け即時予約処理を開始します。")

        # 4. ログイン実行
        if not login(session):
            return
        logged_in = True

        # 5. 【動的再巡回予約ループ】（最大5枠まで連続取得）
        MAX_BOOKINGS = 5
        booked_count = 0
        failed_slot_keys = set()

        for loop_idx in range(1, MAX_BOOKINGS + 1):
            log(f"\n--- [予約ラウンド {loop_idx}/{MAX_BOOKINGS}] 最新の空き枠状況を再巡回中... ---")
            current_slots = scan_all_districts(session)

            # すでに失敗した枠を除外
            valid_slots = []
            for s in current_slots:
                key = (str(s.get("useYmd")), str(s.get("bcd")), str(s.get("icd")), str(s.get("sTime")))
                if key not in failed_slot_keys:
                    valid_slots.append(s)

            if not valid_slots:
                log("=> 対象学校の空き枠がすべて埋まった（または取り切った）ため、予約ループを終了します。")
                break

            target_slot = valid_slots[0]
            slot_key = (str(target_slot.get("useYmd")), str(target_slot.get("bcd")), str(target_slot.get("icd")), str(target_slot.get("sTime")))
            area_bcd = target_slot["_area_bcd"]

            # 最優先の1枠（最長枠）を予約実行
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
