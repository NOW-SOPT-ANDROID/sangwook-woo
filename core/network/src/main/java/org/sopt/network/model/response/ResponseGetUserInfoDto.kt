package org.sopt.network.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.model.Member

@Serializable
data class ResponseGetUserInfoDto(
    @SerialName("authenticationId")
    val authenticationId: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("phone")
    val phone: String,
)

fun ResponseGetUserInfoDto.toCoreModel() = Member(
    id = this.authenticationId,
    nickname = this.nickname,
    phone = this.phone,
)