package org.sopt.main.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.domain.repo.UserDataRepository
import org.sopt.ui.orbit.updateState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
) : ContainerHost<LoginState, LoginSideEffect>, ViewModel() {
    override val container: Container<LoginState, LoginSideEffect> = container(LoginState())

    init {
        getUserData()
        intent {
            container.stateFlow.collect {
                if (it.isAutoLogin) {
                    postSideEffect(LoginSideEffect.LoginSuccess)
                }
            }
        }
    }

    fun getUserData() = intent {
        userDataRepository.getUserData().collect {
            reduce {
                state.copy(
                    registeredId = it.id,
                    registeredPassword = it.pw,
                    registeredHobby = it.hobby,
                    registeredName = it.name,
                    isAutoLogin = it.autoLogin,
                )
            }
        }
    }

    fun signup() = intent {
        postSideEffect(LoginSideEffect.NavigateToSignUp)
    }

    fun login() = intent {
        with(state) {
            when {
                checkRegister() -> {
                    postSideEffect(LoginSideEffect.showSnackbar("회원가입 먼저하셈"))
                }
                matchesUserInfo(state.id, state.password) -> {
                    userDataRepository.setAutoLogin(true)
                }

                else -> postSideEffect(LoginSideEffect.showSnackbar("로그인 실패"))
            }
        }
    }

    fun updateId(id: String) = updateState { copy(id = id) }
    fun updatePw(pw: String) = updateState { copy(password = pw) }
}