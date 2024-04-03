package org.sopt.main.signup

import org.sopt.main.model.User

data class SignupState(
    val id: String = "",
    val password: String = "",
    val name: String = "",
    val hobby: String = ""
)

sealed interface SignupSideEffect {
    data class showSnackbar(val message: String) : SignupSideEffect
    data class SignupSuccess(val user: User) : SignupSideEffect
}