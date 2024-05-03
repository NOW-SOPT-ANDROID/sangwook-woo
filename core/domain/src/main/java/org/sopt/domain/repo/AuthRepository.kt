package org.sopt.domain.repo

import org.sopt.model.Base
import org.sopt.model.Member

interface AuthRepository {
    suspend fun postSignup(member: Member, pw: String): Result<Base<Nothing>>

    suspend fun getUserinfo(): Result<Member?>

    suspend fun postSignin(id: String, pw: String): Result<Base<Nothing>>

    suspend fun patchPassword(
        previousPassword: String,
        newPassword: String,
        newPasswordVerification: String,
    ): Result<Base<Nothing>>
}