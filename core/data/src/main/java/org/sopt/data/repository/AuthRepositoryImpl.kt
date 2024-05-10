package org.sopt.data.repository

import kotlinx.serialization.json.Json
import org.sopt.domain.repo.AuthRepository
import org.sopt.model.Base
import org.sopt.model.Member
import org.sopt.model.exception.ApiError
import org.sopt.network.api.AuthApi
import org.sopt.network.model.request.RequestPatchPasswordDto
import org.sopt.network.model.request.RequestPostSigninDto
import org.sopt.network.model.request.RequestPostSignupDto
import org.sopt.network.model.response.base.BaseResponse
import org.sopt.network.model.response.base.toCoreModelNothingType
import org.sopt.network.model.response.toCoreModel
import retrofit2.HttpException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
) : AuthRepository {
    override suspend fun postSignup(member: Member, pw: String): Result<Base<Nothing>> =
        runCatching {
            authApi.postSignup(
                RequestPostSignupDto(
                    authenticationId = member.id,
                    nickname = member.nickname,
                    password = pw,
                    phone = member.phone
                )
            )
        }.handleResult {
            it.toCoreModelNothingType()
        }


    override suspend fun getUserinfo(): Result<Base<Member>> = runCatching {
        authApi.getUserinfo()
    }.handleResult {
        Base(data = it.data?.toCoreModel(), message = it.message)
    }

    override suspend fun postSignin(id: String, pw: String): Result<Base<Nothing>> = runCatching {
        authApi.postSignin(
            RequestPostSigninDto(
                authenticationId = id,
                password = pw
            )
        )
    }.handleResult {
        it.toCoreModelNothingType()
    }


    override suspend fun patchPassword(
        previousPassword: String,
        newPassword: String,
        newPasswordVerification: String,
    ): Result<Base<Nothing>> = runCatching {
        authApi.patchPassword(
            RequestPatchPasswordDto(
                previousPassword = previousPassword,
                newPassword = newPassword,
                newPasswordVerification = newPasswordVerification
            )
        )
    }.handleResult {
        it.toCoreModelNothingType()
    }


    private inline fun <reified T, V> Result<BaseResponse<T>>.handleResult(action: (BaseResponse<T>) -> Base<V>) =
        this.mapCatching {
            action(it)
        }.recoverCatching { exception ->
            when (exception) {
                is HttpException -> {
                    val json = exception.response()?.errorBody()?.string()
                    if (json != null) {
                        val api = Json.decodeFromString<BaseResponse<Nothing>>(json)
                        val apiError = ApiError(message = api.message)
                        throw apiError
                    } else {
                        throw exception
                    }
                }

                else -> {
                    throw exception
                }
            }
        }
}
