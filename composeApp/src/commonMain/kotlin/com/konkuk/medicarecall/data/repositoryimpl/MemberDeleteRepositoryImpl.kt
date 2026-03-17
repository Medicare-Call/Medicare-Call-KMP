package com.konkuk.medicarecall.data.repositoryimpl

import com.konkuk.medicarecall.data.api.member.MemberDeleteService
import com.konkuk.medicarecall.data.repository.MemberDeleteRepository
import org.koin.core.annotation.Single

@Single
class MemberDeleteRepositoryImpl(
    private val memberDeleteService: MemberDeleteService,
) : MemberDeleteRepository {
    override suspend fun deleteMember(): Result<Unit> = runCatching {
        memberDeleteService.deleteMember()
    }
}
