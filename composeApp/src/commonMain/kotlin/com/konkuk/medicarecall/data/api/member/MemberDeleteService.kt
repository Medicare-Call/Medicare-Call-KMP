package com.konkuk.medicarecall.data.api.member

import de.jensklingenberg.ktorfit.Response
import de.jensklingenberg.ktorfit.http.DELETE

interface MemberDeleteService {
    @DELETE("member}")
    suspend fun deleteMember(): Response<Unit>
}
