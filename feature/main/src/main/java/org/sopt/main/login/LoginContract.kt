package org.sopt.main.login

import org.sopt.main.model.UserModel

data class LoginState(
    val id: String = "",
    val password: String = "",
    val registeredId: String = "",
    val registeredPassword: String = "",
    val registeredName: String = "",
    val registeredHobby: String = "",
    val isAutoLogin: Boolean = false,
) {
    fun checkRegister() = registeredId.isBlank() || registeredPassword.isBlank()

    fun matchesUserInfo(id: String, password: String) =
        registeredId == id && registeredPassword == password

    fun createUser() = UserModel(registeredId, registeredPassword, registeredName, registeredHobby)
}

sealed interface LoginSideEffect {
    data class showSnackbar(val message: String) : LoginSideEffect
    data object LoginSuccess : LoginSideEffect
    data object NavigateToSignUp : LoginSideEffect
    data object SignupSuccess : LoginSideEffect
}