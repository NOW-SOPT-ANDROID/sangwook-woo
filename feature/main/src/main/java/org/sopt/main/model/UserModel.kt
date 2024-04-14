package org.sopt.main.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.sopt.model.User

@Parcelize
data class UserModel(
    val id: String,
    val pw: String,
    val name: String,
    val hobby: String
): Parcelable

fun UserModel.toUser() = User(
    id = id,
    pw = pw,
    name = name,
    hobby = hobby,
    autoLogin = false,
)
