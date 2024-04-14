package org.sopt.main.login

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
import org.sopt.domain.repo.UserDataRepository
import org.sopt.ui.context.ResourceProvider
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val userDataRepository: UserDataRepository,
) : ContainerHost<LoginState, LoginSideEffect>, ViewModel() {
    override val container: Container<LoginState, LoginSideEffect> = container(LoginState())

    init {
        getUserData()
        intent {
            viewModelScope.launch {
                container.stateFlow.collect{
                    if(it.isAutoLogin){
                        postSideEffect(LoginSideEffect.LoginSuccess)
                    }
                }
            }
        }
    }
    fun getUserData() = intent {
        viewModelScope.launch {
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
    }

    fun signup() = intent {
        postSideEffect(LoginSideEffect.NavigateToSignUp)
    }

    fun login(id: String, password: String) = intent {
        with(state) {
            when {
                checkRegister() -> postSideEffect(LoginSideEffect.showSnackbar(getString(R.string.login_not_registered)))
                matchesUserInfo(id, password) -> {
                    userDataRepository.setAutoLogin(true)
                }

                else -> postSideEffect(LoginSideEffect.showSnackbar(getString(R.string.login_login_fail)))
            }
        }
    }

    private fun getString(resId: Int) = resourceProvider.getString(resId)
}