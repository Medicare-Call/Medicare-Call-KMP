package com.konkuk.medicarecall.data.repository

import com.konkuk.medicarecall.data.dto.response.NotificationPageResponseDto

interface NotificationRepository {
    suspend fun changeNotificationState(id: String): Result<Unit>
    suspend fun testNotification(): Result<Unit>
    suspend fun getNotifications(): Result<List<NotificationPageResponseDto>>
}
