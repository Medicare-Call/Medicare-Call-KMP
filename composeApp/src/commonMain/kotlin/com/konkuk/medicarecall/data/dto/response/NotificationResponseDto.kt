package com.konkuk.medicarecall.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class NotificationResponseDto(
    val id: Int,
    val title: String,
    val body: String,
    val isRead: Boolean,
    val createdAt: String,
)
