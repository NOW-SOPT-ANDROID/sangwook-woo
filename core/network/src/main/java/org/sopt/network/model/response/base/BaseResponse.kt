package org.sopt.network.model.response.base

import kotlinx.serialization.Serializable
import org.sopt.model.Base

@Serializable
data class BaseResponse<T>(
    val code: Int,
    val data: T? = null,
    val message: String,
)

fun <T> BaseResponse<T>.toCoreModel(): Base<T> = Base(
    code = this.code,
    data = this.data,
    message = this.message
)

fun <T> BaseResponse<T>.toCoreModelNothingType(): Base<Nothing> = Base(
    code = this.code,
    message = this.message
)