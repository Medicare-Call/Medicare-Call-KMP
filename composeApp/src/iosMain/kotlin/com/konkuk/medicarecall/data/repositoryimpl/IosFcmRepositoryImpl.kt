package com.konkuk.medicarecall.data.repositoryimpl

import com.konkuk.medicarecall.data.repository.FcmRepository

class IosFcmRepositoryImpl : FcmRepository {
    override suspend fun saveFcmToken(token: String) {
        // iOS: APNs token management - to be implemented
    }

    override suspend fun getFcmToken(): String? {
        // iOS: APNs token retrieval - to be implemented
        return null
    }

    override suspend fun clearToken() {
        // iOS: Clear APNs token - to be implemented
    }

    override suspend fun validateAndRefreshTokenIfNeeded(jwtToken: String) {
        // iOS: Token validation - to be implemented
    }
}
