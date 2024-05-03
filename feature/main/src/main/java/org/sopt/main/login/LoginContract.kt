package org.sopt.main.login

data class LoginState(
    val id: String = "",
    val password: String = "",
)

sealed interface LoginSideEffect {
    data class showSnackbar(val message: String) : LoginSideEffect
    data object LoginSuccess : LoginSideEffect
    data object NavigateToSignUp : LoginSideEffect
    data object SignupSuccess : LoginSideEffect
}