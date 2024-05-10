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
    data = this.data,
    message = this.message
)

fun <T> BaseResponse<T>.toCoreModelNothingType(): Base<Nothing> = Base(
    message = this.message
)