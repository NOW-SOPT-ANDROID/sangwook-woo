package org.sopt.network.model.response.base

import kotlinx.serialization.Serializable
import org.sopt.model.Base

@Serializable
data class ApiError(
    val code: Int,
    val message: String,
)

fun ApiError.toCoreModel(): Base<Nothing> = Base(
    code = this.code,
    message = this.message
)
