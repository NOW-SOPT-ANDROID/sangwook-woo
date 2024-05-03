package org.sopt.mypage.modifypassword

data class ModifyPasswordState(
    val previousPassword: String = "",
    val newPassword: String = "",
    val newPasswordVerification: String = "",
)

sealed interface ModifyPasswordSideEffect {
    data class ShowSnackbar(val msg: String) : ModifyPasswordSideEffect
    data object ModifySuccess : ModifyPasswordSideEffect
}