package org.sopt.main.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.main.model.User
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
) : ContainerHost<LoginState, LoginSideEffect>, ViewModel() {
    override val container: Container<LoginState, LoginSideEffect> = container(LoginState())

    fun signup() = intent {
        postSideEffect(LoginSideEffect.NavigateToSignUp)
    }

    fun login() = intent {
        with(state) {
            when {
                checkRegister() -> postSideEffect(LoginSideEffect.showSnackbar("회원가입 먼저하셈"))
                matchesUserInfo(state.id, state.password) -> postSideEffect(LoginSideEffect.LoginSuccess(createUser()))
                else -> postSideEffect(LoginSideEffect.showSnackbar("로그인 실패"))
            }
        }
    }
    private fun LoginState.checkRegister() = registeredId.isBlank() || registeredPassword.isBlank()

    private fun LoginState.matchesUserInfo(id: String, password: String) = registeredId == id && registeredPassword == password

    private fun LoginState.createUser() = User(registeredId, registeredPassword, name, hobby)

    fun updateId(id: String) = updateState { copy(id = id) }
    fun updatePw(pw: String) = updateState { copy(password = pw) }

    private fun updateState(reducer: LoginState.() -> LoginState) = intent {
        reduce { state.reducer() }
    }

    fun signupSuccess(user: User?) = intent {
        postSideEffect(LoginSideEffect.SignupSuccess)

        reduce {
            state.copy(
                registeredId = user?.id.orEmpty(),
                registeredPassword = user?.pw.orEmpty(),
                name = user?.name.orEmpty(),
                hobby = user?.hobby.orEmpty()
            )
        }
    }
}