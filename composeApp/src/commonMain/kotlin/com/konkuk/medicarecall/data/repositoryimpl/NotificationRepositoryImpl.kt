package com.konkuk.medicarecall.data.repositoryimpl

import com.konkuk.medicarecall.data.api.fcm.NotificationService
import com.konkuk.medicarecall.data.dto.response.NotificationPageResponseDto
import com.konkuk.medicarecall.data.repository.NotificationRepository
import com.konkuk.medicarecall.data.util.handleResponse
import org.koin.core.annotation.Single

@Single
class NotificationRepositoryImpl(
    private val notificationService: NotificationService,
) : NotificationRepository {
    override suspend fun changeNotificationState(id: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun testNotification(): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getNotifications():
        Result<List<NotificationPageResponseDto>> = runCatching {
        notificationService.getNotifications().handleResponse()
    }
}
