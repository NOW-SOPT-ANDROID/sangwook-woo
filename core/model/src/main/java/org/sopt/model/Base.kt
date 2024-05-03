package org.sopt.model

data class Base<T>(
    val code: Int,
    val data: T? = null,
    val message: String,
)

