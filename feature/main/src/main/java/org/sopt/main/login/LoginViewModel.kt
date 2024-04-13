package org.sopt.main.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.designsystem.R
import org.sopt.main.model.User
import org.sopt.ui.context.ResourceProvider
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider
) : ContainerHost<LoginState, LoginSideEffect>, ViewModel() {
    override val container: Container<LoginState, LoginSideEffect> = container(LoginState())

    fun signup() = intent {
        postSideEffect(LoginSideEffect.NavigateToSignUp)
    }

    fun login(id: String, password: String) = intent {
        with(state) {
            when {
                checkRegister() -> postSideEffect(LoginSideEffect.showSnackbar(getString(R.string.login_not_registered)))
                matchesUserInfo(id, password) -> postSideEffect(LoginSideEffect.LoginSuccess(createUser()))
                else -> postSideEffect(LoginSideEffect.showSnackbar(getString(R.string.login_login_fail)))
            }
        }
    }

    private fun getString(resId: Int) = resourceProvider.getString(resId)

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