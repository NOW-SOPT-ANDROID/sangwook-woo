package org.sopt.main.model

import org.sopt.model.User

data class UserModel(
    val id: String = "",
    val pw: String = "",
    val name: String = "",
    val hobby: String = "",
)

fun UserModel.toUser() = User(
    id = id,
    pw = pw,
    name = name,
    hobby = hobby,
    autoLogin = false,
)
