package org.sopt.network.model.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPostSigninDto(
    @SerialName("authenticationId")
    val authenticationId: String,
    @SerialName("password")
    val password: String,
)