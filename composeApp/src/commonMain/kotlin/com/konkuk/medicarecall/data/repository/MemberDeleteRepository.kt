package com.konkuk.medicarecall.data.repository

interface MemberDeleteRepository {
    suspend fun deleteMember(): Result<Unit>
}
