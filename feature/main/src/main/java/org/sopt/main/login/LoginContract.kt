package org.sopt.main.login

import org.sopt.main.model.User

data class LoginState(
    val id: String = "",
    val password: String = "",
    val registeredId: String = "",
    val registeredPassword: String = "",
    val name: String = "",
    val hobby: String = ""
)

sealed interface LoginSideEffect {
    data class showSnackbar(val message: String) : LoginSideEffect
    data class LoginSuccess(val user: User) : LoginSideEffect
    data object NavigateToSignUp : LoginSideEffect
    data object SignupSuccess : LoginSideEffect
}