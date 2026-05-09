package com.konkuk.medicarecall.domain.model.type

enum class HomeStatusType(val title: String) {
    GOOD("양호"),
    ATTENTION("관심"),
    WARNING("주의"),
    UNRECORDED("미기록")
}
