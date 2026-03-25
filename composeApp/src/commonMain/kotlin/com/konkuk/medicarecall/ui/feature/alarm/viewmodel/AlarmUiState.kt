package com.konkuk.medicarecall.ui.feature.alarm.viewmodel

import com.konkuk.medicarecall.data.dto.response.NotificationPageResponseDto

data class AlarmUiState(
    val alarmPages: List<NotificationPageResponseDto> = emptyList(),
    val errorMessage: String? = null,
)
