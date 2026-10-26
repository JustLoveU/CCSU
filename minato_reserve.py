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

# 検索関連エンドポイント
SEARCH_URL = f"{BASE_URL}/rsvWOpeInstSrchVacantAction.do"
DAILY_URL = f"{BASE_URL}/rsvWOpeUnreservedDailyAction.do"
AJAX_URL = f"{BASE_URL}/rsvWOpeUnreservedSearchAjaxAction.do"

# 予約関連エンドポイント（品川区と同型システム）
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

# 予約条件
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

# 安全スイッチ: Trueで最終確定POSTまで自動実行 / Falseは空き確認のみ
AUTO_BOOK = True

OUTPUT_DIR = Path("output")
OUTPUT_DIR.mkdir(parents=True, exist_ok=True)
# ==================================================


def log(msg: str):
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    formatted = f"[{timestamp}] {msg}"
    print(formatted)
    with open(OUTPUT_DIR / "execution.log", "a", encoding="utf-8") as f:
        f.write(formatted + "\n")


def send_mail(subject: str, body: str):
    """Yahoo! SMTP経由でメール送信 (SSL 465)"""
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


def login(session: requests.Session) -> bool:
    """ログイン認証を行い、利用者名を表示して成否を厳密判定"""
    log("1. ログイン処理を開始します...")

    # ログイン画面へ遷移
    r = session.post(LOGIN_PAGE_URL, data={"displayNo": "pawab2000"}, timeout=20)
    r.encoding = "cp932"
    soup = BeautifulSoup(r.text, "html.parser")

    jkey_tag = soup.find("input", {"name": "loginJKey"})
    login_jkey = jkey_tag.get("value", "") if jkey_tag else ""

    # パスワード分割（loginCharPass）ペイロード構築
    payload = [
        ("userId", USER_ID),
        ("password", PASSWORD),
        ("fcflg", ""),
        ("displayNo", "pawab2100"),
        ("loginJKey", login_jkey),
    ]
    for ch in PASSWORD:
        payload.append(("loginCharPass", ch))

    # 認証POST
    r_auth = session.post(LOGIN_URL, data=payload, timeout=20)
    r_auth.encoding = "cp932"
    (OUTPUT_DIR / "login_result.html").write_text(r_auth.text, encoding="utf-8", errors="replace")

    # ログイン判定
    if "ログアウト" in r_auth.text:
        # 利用者名の抽出
        soup_auth = BeautifulSoup(r_auth.text, "html.parser")
        user_name_elem = soup_auth.find(id="userName") or soup_auth.find("span", class_="dropdown-item-text")
        user_name = user_name_elem.get_text(strip=True) if user_name_elem else f"利用者ID: {USER_ID}"
        log(f"=> ★★★ ログイン成功！ 認証者: 『{user_name}』 ★★★")
        return True
    else:
        err_elem = BeautifulSoup(r_auth.text, "html.parser").find(id="errormsg")
        err_msg = err_elem.get_text(strip=True) if err_elem else "IDまたはパスワード不一致"
        log(f"=> 【ログイン失敗】 {err_msg}")
        return False


def logout(session: requests.Session):
    """cron終了時に必ずログアウトしてセッションを破棄"""
    try:
        log("ログアウト処理を実行中...")
        session.post(LOGOUT_URL, data={"displayNo": "pawab2000"}, timeout=10)
        log("=> ログアウト完了（セッション解放）")
    except Exception as e:
        log(f"ログアウト通信エラー: {e}")


def extract_form_data(html: str) -> dict:
    """品川スクリプト同型：daily_page.html から JS定義の formData を抽出"""
    m = re.search(r'var formData = (\{.*?\});', html, re.S)
    if not m:
        return None
    return json.loads(m.group(1))


def do_search_district(session: requests.Session, area_name: str, area_bcd: str) -> tuple[list[dict], str]:
    """指定地区の空き状況を照会し、枠一覧と遷移用HTMLを返却"""
    # 1. 検索条件POST
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
    search_payload.append(("timezone", "9"))  # 終日

    session.post(SEARCH_URL, data=search_payload, timeout=20)

    # 2. 日次空き画面へ遷移
    r_daily = session.post(DAILY_URL, data={"displayNo": "prwrc2000"}, timeout=20)
    r_daily.encoding = "cp932"
    daily_html = r_daily.text

    form_data = extract_form_data(daily_html)
    if not form_data:
        return [], daily_html

    # 3. 画面定義の formData をそのまま使って Ajax エンドポイントを直撃
    headers = {
        "X-Requested-With": "XMLHttpRequest",
        "Referer": DAILY_URL,
        "Origin": BASE_URL,
        "Accept": "application/json, text/javascript, */*; q=0.01",
        "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
    }

    form_data["offset"] = 0
    form_data["limit"] = 100

    r_ajax = session.post(AJAX_URL, data=form_data, headers=headers, timeout=20)
    r_ajax.encoding = "cp932"

    try:
        data = r_ajax.json()
        results = data.get("results", [])
        return results, daily_html
    except Exception:
        return [], daily_html


def execute_booking(session: requests.Session, candidate_raw: dict, daily_html: str, area_name: str, area_bcd: str) -> bool:
    """品川区同型パイプライン：予約申込 -> 利用規約同意 -> 予約確定"""
    mansion = candidate_raw.get("bcdNm", "")
    facility = candidate_raw.get("icdNm", "")
    use_date = str(candidate_raw.get("useYmd", ""))
    date_str = f"{use_date[:4]}-{use_date[4:6]}-{use_date[6:8]}"
    time_str = f"{candidate_raw.get('sJTime2', '')}〜{candidate_raw.get('eJTime2', '')}"
    slot_info = f"{date_str} {time_str} | {mansion} ({facility})"

    log(f"★ 予約手続きを開始します: {slot_info}")

    if not AUTO_BOOK:
        log("【安全確認モード】AUTO_BOOK=False のため、予約POSTは送信せずスキップします。")
        return False

    # ---------------- Step 1: 予約申込画面へ遷移 ----------------
    soup_daily = BeautifulSoup(daily_html, "html.parser")
    apply_payload = {}
    for tag in soup_daily.find_all("input"):
        name = tag.get("name")
        if name:
            apply_payload[name] = tag.get("value", "")

    # 条件と対象枠の上書き（品川スクリプト完全互換）
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

    log("Step 1: 予約申込画面へPOST送信...")
    r_apply = session.post(RESERVE_APPLY_URL, data=apply_payload, headers=headers, timeout=20)
    r_apply.encoding = "cp932"
    (OUTPUT_DIR / "01_apply_response.html").write_text(r_apply.text, encoding="utf-8", errors="replace")

    current_html = r_apply.text

    # ---------------- Step 2: 利用規約画面がある場合の同意処理 ----------------
    if "利用規約" in current_html:
        log("Step 1.5: 利用規約画面を検知。同意フラグ(ruleFg=1)を送信します...")
        soup_agree = BeautifulSoup(current_html, "html.parser")
        agree_payload = {tag.get("name"): tag.get("value", "") for tag in soup_agree.find_all("input") if tag.get("name")}
        agree_payload["ruleFg"] = "1"

        r_agree = session.post(RESERVE_AGREEMENT_URL, data=agree_payload, headers=headers, timeout=20)
        r_agree.encoding = "cp932"
        (OUTPUT_DIR / "02_agree_confirm.html").write_text(r_agree.text, encoding="utf-8", errors="replace")
        current_html = r_agree.text

    if "確認" not in current_html and "予約" not in current_html:
        log("【エラー】予約確認画面への到達に失敗しました。01_apply_response.html を確認してください。")
        return False

    log("Step 2: 予約確認画面へ正常到達。利用人数の設定と確定POSTを準備します...")

    # ---------------- Step 3: 最終予約確定 ----------------
    soup_confirm = BeautifulSoup(current_html, "html.parser")
    final_payload = {tag.get("name"): tag.get("value", "") for tag in soup_confirm.find_all("input") if tag.get("name")}

    # 人数・目的・画面IDの確実なセット
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

    log("Step 3: 最終確定POST送信中...")
    r_final = session.post(RESERVE_CONFIRM_URL, data=final_payload, headers=headers, timeout=20)
    r_final.encoding = "cp932"
    final_html = r_final.text
    (OUTPUT_DIR / "03_final_confirmed.html").write_text(final_html, encoding="utf-8", errors="replace")

    # ---------------- Step 4: 予約完了判定とメール発報 ----------------
    if "完了" in final_html or "受付番号" in final_html or "予約番号" in final_html:
        # 予約番号の抽出
        rsv_no_match = re.search(r'(?:予約番号|受付番号)[：:\s]*([A-Za-z0-9\-]+)', final_html)
        rsv_no = rsv_no_match.group(1) if rsv_no_match else "画面参照"

        log(f"★★★ 【祝・予約完了】 予約が正常に確定しました！ 予約番号: {rsv_no} ★★★")

        # メール通知発報
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
            f"詳細はマイページの予約確認画面をご確認ください。"
        )
        send_mail(mail_subject, mail_body)
        return True
    else:
        log("【警告】完了画面のキーワードが見つかりませんでした。03_final_confirmed.html をご確認ください。")
        return False


def main():
    log("=== 港区学校施設 自動監視・予約ボット 起動 ===")
    log(f"検索開始日: {TARGET_DATE} / 期間: {TARGET_DAYS}日間 / 人数: {APPLY_NUM}人")

    if not USER_ID or not PASSWORD:
        log("【エラー】RSV_USER_ID または RSV_PASSWORD が設定されていません。")
        sys.exit(1)

    session = requests.Session()
    session.headers.update({
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36",
        "Referer": INDEX_URL,
        "Origin": BASE_URL,
    })

    try:
        # 1. トップページ接続
        session.get(INDEX_URL, timeout=15)

        # 2. ログイン認証
        if not login(session):
            return

        # 3. 全5地区の学校施設を巡回
        booked = False
        for area_name, area_bcd in AREA_MAP.items():
            t0 = time.time()
            slots, daily_html = do_search_district(session, area_name, area_bcd)
            elapsed = (time.time() - t0) * 1000

            if slots:
                log(f"[{area_name}地区] ★ 空き枠を {len(slots)} 件検出！ ({elapsed:.1f}ms)")
                for idx, slot in enumerate(slots, 1):
                    use_d = str(slot.get('useYmd', ''))
                    d_str = f"{use_d[:4]}-{use_d[4:6]}-{use_d[6:8]}"
                    t_str = f"{slot.get('sJTime2', '')}〜{slot.get('eJTime2', '')}"
                    fac = slot.get('bcdNm', '')
                    rm = slot.get('icdNm', '')
                    log(f"   ({idx}) {d_str} {t_str} | {fac} ({rm})")

                # 最優先枠を即時予約
                if not booked:
                    if execute_booking(session, slots[0], daily_html, area_name, area_bcd):
                        booked = True
                        break
            else:
                log(f"[{area_name}地区] 空きなし ({elapsed:.1f}ms)")

            time.sleep(0.3)

    finally:
        # 4. cron実行のために必ずセッションをログアウト
        logout(session)
        log("=== 監視処理 終了 ===")


if __name__ == "__main__":
    main()
