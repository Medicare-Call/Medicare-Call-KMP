package com.konkuk.medicarecall.ui.common.util

/**
 * @param dateStr yyyy-MM-dd
 * @return yyyy년 M월 d일
 */
fun formatDateToKorean(dateStr: String): String {
    return try {
        val parts = dateStr.split("-")
        val year = parts[0]
        val month = parts[1].toInt()
        val day = parts[2].toInt()
        "${year}년 ${month}월 ${day}일"
    } catch (e: Exception) {
        dateStr // 파싱 실패 시 원래 문자열 반환
    }
}
