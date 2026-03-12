package com.konkuk.medicarecall.domain.model.type

// 아침, 점심, 저녁 enum
enum class MedicationTime(val displayName: String) {
    MORNING("아침"),
    LUNCH("점심"),
    DINNER("저녁"),
    ;

    fun toDoseLabel(): String = "${displayName}약"

    companion object {
        fun fromRaw(value: String?): MedicationTime? {
            return when (value?.trim()?.uppercase()) {
                "MORNING" -> MORNING
                "LUNCH" -> LUNCH
                "DINNER" -> DINNER
                else -> null
            }
        }
    }
}
