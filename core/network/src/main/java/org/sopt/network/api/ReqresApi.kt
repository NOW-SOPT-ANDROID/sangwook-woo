package org.sopt.network.api

import org.sopt.network.model.response.ResponseGetListUsersDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqresApi {
    @GET("/$API/$USERS")
    suspend fun getUsers(
        @Query("page") page: Int,
    ): ResponseGetListUsersDto

    companion object {
        private const val API = "api"
        private const val USERS = "users"
    }
}