#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import os
import sys
import re
import json
import time
import smtplib
import urllib.parse
from datetime import datetime, timedelta
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
APPLY_NUM = "15"                        # 利用人数: 15人

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
    "高輪台小学校", "白金小学校", "三田中学校", "高松中学校", "白金の丘学園",
    # 芝浦港南地区
    "芝浦小学校", "芝浜小学校", "港南小学校", "港南中学校", "お台場学園港陽小・中学校",
    # 芝地区
    "御成門学園御成門小学校", "芝小学校", "赤羽小学校", "御成門学園御成門中学校"
}

# 除外校
EXCLUDE_SCHOOLS = {"笄小学校", "東町小学校", "御田小学校（仮校舎）"}

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


def log(msg: str):
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
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
        log("=> ★ 予約完了通知メールを送信しました！")
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


def search_unlogged_district(session: requests.Session, area_bcd: str) -> list[dict]:
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


def login(session: requests.Session) -> bool:
    log("▶ 対象学校の空き枠を検知したため、認証ログインを実行します...")

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


def execute_booking(session: requests.Session, candidate_raw: dict, area_bcd: str) -> bool:
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

    # 修正箇所: 辞書キーの区切りを ':' に統一
    apply_payload.update({
        "date": "5",
        "daystart": TARGET_DATE,
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
        "Origin": BASE_URL,
        "Content-Type": "application/x-www-form-urlencoded",
        "User-Agent": "Mozilla/5.0"
    }

    # Step 1: 申込画面へPOST
    r_apply = session.post(RESERVE_APPLY_URL, data=apply_payload, headers=headers, timeout=20)
    r_apply.encoding = "cp932"
    current_html = r_apply.text

    # Step 2: 利用規約画面がある場合は同意処理
    if "利用規約" in current_html:
        soup_agree = BeautifulSoup(current_html, "html.parser")
        agree_payload = {tag.get("name"): tag.get("value", "") for tag in soup_agree.find_all("input") if tag.get("name")}
        agree_payload["ruleFg"] = "1"

        r_agree = session.post(RESERVE_AGREEMENT_URL, data=agree_payload, headers=headers, timeout=20)
        r_agree.encoding = "cp932"
        current_html = r_agree.text

    if "確認" not in current_html and "予約" not in current_html:
        log(f"【エラー】『{mansion}』の予約確認画面へ進めませんでした（利用制限・重複等の可能性）。")
        return False

    # Step 3: 人数入力と確定POST
    soup_confirm = BeautifulSoup(current_html, "html.parser")
    final_payload = {tag.get("name"): tag.get("value", "") for tag in soup_confirm.find_all("input") if tag.get("name")}

    final_payload.update({
        "applyNum": APPLY_NUM,
        "ninzu": APPLY_NUM,
        "stimeZoneNo": str(candidate_raw.get("tZoneNo", "")),
        "etimeZoneNo": str(candidate_raw.get("tZoneNo", "")),
        "ppsCd": str(candidate_raw.get("ppsCd", "")),
        "ppsdCd": str(candidate_raw.get("ppsClsCd", "")),
        "purpose": TARGET_PURPOSE,
        "displayNo": "prwea1000",
        "selectRsvDetailNo": "0",
    })

    r_final = session.post(RESERVE_CONFIRM_URL, data=final_payload, headers=headers, timeout=20)
    r_final.encoding = "cp932"
    final_html = r_final.text

    # Step 4: 完了判定
    if "完了" in final_html or "受付番号" in final_html or "予約番号" in final_html:
        rsv_no_match = re.search(r'(?:予約番号|受付番号)[：:\s]*([A-Za-z0-9\-]+)', final_html)
        rsv_no = rsv_no_match.group(1) if rsv_no_match else "画面参照"

        log(f"★★★ 【予約完了】 {mansion} / 予約番号: {rsv_no} ★★★")

        mail_subject = f"【予約完了通知】港区学校施設予約が確定しました ({mansion})"
        mail_body = (
            f"港区施設予約システムにて自動予約が完了しました。\n\n"
            f"■ 予約内容\n"
            f"- 予約番号: {rsv_no}\n"
            f"- 施設名: {mansion}\n"
            f"- 室場名: {facility}\n"
            f"- 利用日時: {date_str} {time_str}\n"
            f"- 利用人数: {APPLY_NUM}人\n"
            f"- 利用目的: バドミントン\n"
            f"- 確定時刻: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}\n\n"
            f"詳細はマイページの予約一覧をご確認ください。"
        )
        send_mail(mail_subject, mail_body)
        return True
    else:
        log(f"【警告】『{mansion}』の完了画面キーワードが確認できませんでした。")
        return False


def main():
    log("=== 港区学校施設 巡回ボット 起動（優先順位・全予約対応版） ===")
    log(f"検索開始日: {TARGET_DATE} / 期間: {TARGET_DAYS}日間 / 人数: {APPLY_NUM}人")

    session = requests.Session()
    session.headers.update({
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36",
        "Referer": INDEX_URL,
        "Origin": BASE_URL,
    })

    logged_in = False

    try:
        session.get(INDEX_URL, timeout=10)
        all_detected_school_slots = []

        # 1. 未ログインのまま全5地区を高速巡回・全ページ取得
        for area_name, area_bcd in AREA_MAP.items():
            t0 = time.time()
            all_slots = search_unlogged_district(session, area_bcd)
            elapsed = (time.time() - t0) * 1000

            target_slots = []
            for slot in all_slots:
                mansion = slot.get("bcdNm", "")
                if is_target_school(mansion):
                    slot["_area_name"] = area_name
                    slot["_area_bcd"] = area_bcd
                    target_slots.append(slot)

            if target_slots:
                log(f"[{area_name}地区] ★ 対象学校の空き枠を {len(target_slots)} 件検出！ ({elapsed:.1f}ms)")
                all_detected_school_slots.extend(target_slots)
            else:
                log(f"[{area_name}地区] 対象学校の空きなし (全{len(all_slots)}件走査 / {elapsed:.1f}ms)")

            time.sleep(0.2)

        # 2. 対象学校枠が0件ならログインせず終了（監査ログゼロ）
        if not all_detected_school_slots:
            log("=> 全地区で対象学校の空き枠はありませんでした。ログインせずに巡回を終了します。")
            return

        # 3. 優先順位ソート (高松中 -> 御成門中 -> 芝浜小 -> 三田中 -> その他日時順)
        all_detected_school_slots.sort(key=get_school_priority)

        log("=" * 70)
        log(f"【予約候補リスト（優先順位ソート済: 全 {len(all_detected_school_slots)} 件）】")
        for rank, s in enumerate(all_detected_school_slots, 1):
            use_d = str(s.get('useYmd', ''))
            d_str = f"{use_d[:4]}-{use_d[4:6]}-{use_d[6:8]}"
            t_str = f"{s.get('sJTime2', '')}〜{s.get('eJTime2', '')}"
            fac = s.get('bcdNm', '')
            rm = s.get('icdNm', '')
            log(f"  [{rank}位] {d_str} {t_str} | {fac} ({rm}) [{s['_area_name']}地区]")
        log("=" * 70)

        # 4. ここで初めてログインを実行
        if not login(session):
            return
        logged_in = True

        # 5. 複数候補を優先順位順にすべて予約実行
        success_count = 0
        for slot in all_detected_school_slots:
            mansion = slot.get("bcdNm", "")
            area_bcd = slot["_area_bcd"]

            if execute_booking(session, slot, area_bcd):
                success_count += 1

            time.sleep(1.5)

        log(f"=== 予約処理完了: 合計 {success_count} / {len(all_detected_school_slots)} 件 確定 ===")

    finally:
        if logged_in:
            logout(session)
        log("=== 巡回処理 終了 ===")


if __name__ == "__main__":
    main()
