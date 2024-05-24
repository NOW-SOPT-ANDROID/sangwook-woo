package org.sopt.main.login

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.domain.repo.AuthRepository
import org.sopt.main.login.navigation.LoginRoute
import org.sopt.model.exception.ApiError
import org.sopt.ui.orbit.updateState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    savedStateHandle: SavedStateHandle,
) : ContainerHost<LoginState, LoginSideEffect>, ViewModel() {
    override val container: Container<LoginState, LoginSideEffect> = container(LoginState())

    private val isSignupSuccess: Boolean =
        savedStateHandle.get<String>(LoginRoute.IS_SIGNUP_SUCESS).toBoolean()

    init {
        intent {
            if (isSignupSuccess) {
                postSideEffect(LoginSideEffect.SignupSuccess)
            }
        }
    }

    fun signup() = intent {
        postSideEffect(LoginSideEffect.NavigateToSignUp)
    }

    fun login() = intent {
        authRepository.postSignin(
            id = state.id,
            pw = state.password
        ).onSuccess {
            postSideEffect(LoginSideEffect.LoginSuccess)
        }.onFailure {
            if(it is ApiError){
                postSideEffect(LoginSideEffect.showSnackbar(it.message))
            } else {
                postSideEffect(LoginSideEffect.showSnackbar("로그인 실패"))
            }
        }
    }


    fun updateId(id: String) = updateState {
        copy(id = id)
    }
    fun updatePw(pw: String) = updateState { copy(password = pw) }
}