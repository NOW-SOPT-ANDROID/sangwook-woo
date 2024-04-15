package org.sopt.model

data class User(
    val id: String = "",
    val pw: String = "",
    val name: String = "",
    val hobby: String = "",
    val autoLogin: Boolean = false,
)
