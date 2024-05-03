package org.sopt.network.api

import org.sopt.network.model.request.RequestPatchPasswordDto
import org.sopt.network.model.request.RequestPostSigninDto
import org.sopt.network.model.request.RequestPostSignupDto
import org.sopt.network.model.response.ResponseGetUserInfoDto
import org.sopt.network.model.response.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthApi {
    @POST("/$MEMBER/$JOIN")
    suspend fun postSignup(
        @Body requestPostSignupDto: RequestPostSignupDto,
    ): BaseResponse<Unit>

    @GET("/$MEMBER/$INFO")
    suspend fun getUserinfo(
    ): BaseResponse<ResponseGetUserInfoDto>

    @POST("/$MEMBER/$LOGIN")
    suspend fun postSignin(
        @Body requestPostSigninDto: RequestPostSigninDto,
    ): BaseResponse<Unit>

    @PATCH("/$MEMBER/$PASSWORD")
    suspend fun patchPassword(
        @Body requestPatchPasswordDto: RequestPatchPasswordDto,
    ): BaseResponse<Unit>

    companion object {
        private const val MEMBER = "member"
        private const val JOIN = "join"
        private const val INFO = "info"
        private const val LOGIN = "login"
        private const val PASSWORD = "password"
    }
}