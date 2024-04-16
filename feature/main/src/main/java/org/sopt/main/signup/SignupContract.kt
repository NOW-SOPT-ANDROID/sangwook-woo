package org.sopt.main.signup

data class SignupState(
    val id: String = "",
    val password: String = "",
    val name: String = "",
    val hobby: String = "",
)

sealed interface SignupSideEffect {
    data class showSnackbar(val message: String) : SignupSideEffect
    data object SignupSuccess : SignupSideEffect
}