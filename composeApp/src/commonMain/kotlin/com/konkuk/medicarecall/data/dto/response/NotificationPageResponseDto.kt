package com.konkuk.medicarecall.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class NotificationPageResponseDto(
    val totalPages: Int,
    val currentPageNumber: Int,
    val currentElements: Int,
    val notifications: List<NotificationResponseDto>,
)
