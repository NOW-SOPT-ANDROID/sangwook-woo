package org.sopt.data.repository

import kotlinx.serialization.json.Json
import org.sopt.domain.repo.AuthRepository
import org.sopt.model.Base
import org.sopt.model.Member
import org.sopt.network.api.AuthApi
import org.sopt.network.model.request.RequestPatchPasswordDto
import org.sopt.network.model.request.RequestPostSigninDto
import org.sopt.network.model.request.RequestPostSignupDto
import org.sopt.network.model.response.base.ApiError
import org.sopt.network.model.response.base.BaseResponse
import org.sopt.network.model.response.base.toCoreModel
import org.sopt.network.model.response.base.toCoreModelNothingType
import org.sopt.network.model.response.toCoreModel
import retrofit2.HttpException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
) : AuthRepository {
    override suspend fun postSignup(member: Member, pw: String): Result<Base<Nothing>> {
        val result = runCatching {
            authApi.postSignup(
                RequestPostSignupDto(
                    authenticationId = member.id,
                    nickname = member.nickname,
                    password = pw,
                    phone = member.phone
                )
            )
        }
        return handleResult(result)
    }

    override suspend fun getUserinfo(): Result<Member> = runCatching {
        authApi.getUserinfo().data!!.toCoreModel()
    }

    override suspend fun postSignin(id: String, pw: String): Result<Base<Nothing>> {
        val result = runCatching {
            authApi.postSignin(
                RequestPostSigninDto(
                    id,
                    pw
                )
            )
        }
        return handleResult(result)
    }

    override suspend fun patchPassword(
        previousPassword: String,
        newPassword: String,
        newPasswordVerification: String,
    ): Result<Base<Nothing>> {
        val result = runCatching {
            authApi.patchPassword(
                RequestPatchPasswordDto(
                    previousPassword = previousPassword,
                    newPassword = newPassword,
                    newPasswordVerification = newPasswordVerification
                )
            )
        }
        return handleResult(result)
    }

    private fun handleResult(result: Result<BaseResponse<Unit>>) =
        when (val exception = result.exceptionOrNull()) {
            null -> result.map {
                it.toCoreModelNothingType()
            }

            is HttpException -> {
                val json = exception.response()?.errorBody()?.string()
                val api = Json.decodeFromString<ApiError>(json!!)
                Result.success(
                    api.toCoreModel()
                )
            }

            else -> {
                Result.failure(exception)
            }
        }
}