package org.sopt.main.login

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.designsystem.R
import org.sopt.domain.repo.AuthRepository
import org.sopt.domain.repo.UserDataRepository
import org.sopt.ui.context.ResourceProvider
import org.sopt.ui.orbit.updateState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ContainerHost<LoginState, LoginSideEffect>, ViewModel() {
    override val container: Container<LoginState, LoginSideEffect> = container(LoginState())

    fun signup() = intent {
        postSideEffect(LoginSideEffect.NavigateToSignUp)
    }

    fun login() = intent {
        authRepository.postSignin(
            id = state.id,
            pw = state.password
        ).onSuccess {
            if (it.code !in 200..299) {
                postSideEffect(LoginSideEffect.showSnackbar(it.message))
            } else {
                postSideEffect(LoginSideEffect.LoginSuccess)
            }
        }.onFailure {
            Log.e("throwable", it.toString())
        }
    }


    fun updateId(id: String) = updateState { copy(id = id) }
    fun updatePw(pw: String) = updateState { copy(password = pw) }
}