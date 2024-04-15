package org.sopt.data.model

import org.sopt.datastore.UserData
import org.sopt.model.User

fun UserData.toUser() = User(
    id = id ?: "",
    pw = pw ?: "",
    name = name ?: "",
    hobby = hobby ?: "",
    autoLogin = isAutoLogin,
)