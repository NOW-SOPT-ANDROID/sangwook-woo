package org.sopt.main.signup

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
class SignupViewModel @Inject constructor() : ContainerHost<SignupState, SignupSideEffect>, ViewModel() {
    override val container: Container<SignupState, SignupSideEffect> = container(SignupState())

    fun signup() = intent {
        when {
            state.id.length !in 6..10 -> {
                postSideEffect(SignupSideEffect.showSnackbar("아이디 6~10"))
            }

            state.password.length !in 8..12 -> {
                postSideEffect(SignupSideEffect.showSnackbar("비번 8~12"))
            }

            state.name.isBlank() -> {
                postSideEffect(SignupSideEffect.showSnackbar("이름 써라"))
            }

            state.hobby.isBlank() -> {
                postSideEffect(SignupSideEffect.showSnackbar("취미 써라"))
            }

            else -> {
                postSideEffect(
                    SignupSideEffect.SignupSuccess(
                        User(
                            state.id,
                            state.password,
                            state.name,
                            state.hobby
                        )
                    )
                )
            }
        }
    }

    fun updateId(id: String) = updateState { copy(id = id) }
    fun updatePw(pw: String) = updateState { copy(password = pw) }
    fun updateName(name: String) = updateState { copy(name = name) }
    fun updateHobby(hobby: String) = updateState { copy(hobby = hobby) }

    private fun updateState(reducer: SignupState.() -> SignupState) = intent {
        reduce { state.reducer() }
    }
}