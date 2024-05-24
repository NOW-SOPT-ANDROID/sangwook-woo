package org.sopt.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.model.ReqresUser

@Serializable
data class ResponseGetListUsersDto(
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("data")
    val data: List<UserData>,
    @SerialName("support")
    val support: Support,
) {
    @Serializable
    data class UserData(
        @SerialName("id")
        val id: Int? = null,
        @SerialName("email")
        val email: String? = null,
        @SerialName("first_name")
        val firstName: String? = null,
        @SerialName("last_name")
        val lastName: String? = null,
        @SerialName("avatar")
        val avatar: String? = null,
    )

    @Serializable
    data class Support(
        @SerialName("url")
        val url: String,
        @SerialName("text")
        val text: String,
    )
}

fun toReqresUser(data: List<ResponseGetListUsersDto.UserData>): List<ReqresUser> = data.map { it ->
    ReqresUser(
        id = it.id,
        email = it.email,
        firstName = it.firstName,
        lastName = it.lastName,
        avatar = it.avatar
    )
}
