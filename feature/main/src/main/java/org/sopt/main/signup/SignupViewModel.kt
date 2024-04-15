package org.sopt.main.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.designsystem.R
import org.sopt.domain.repo.UserDataRepository
import org.sopt.main.model.UserModel
import org.sopt.main.model.toUser
import org.sopt.ui.context.ResourceProvider
import org.sopt.ui.orbit.updateState
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val userDataRepository: UserDataRepository,
) : ContainerHost<SignupState, SignupSideEffect>, ViewModel() {
    override val container: Container<SignupState, SignupSideEffect> = container(SignupState())

    fun signup() = intent {
        when {
            state.id.length !in 6..10 -> {
                postSideEffect(SignupSideEffect.showSnackbar(resourceProvider.getString(R.string.signup_validid)))
            }

            state.password.length !in 8..12 -> {
                postSideEffect(SignupSideEffect.showSnackbar(resourceProvider.getString(R.string.signup_validpw)))
            }

            state.name.isBlank() -> {
                postSideEffect(SignupSideEffect.showSnackbar(resourceProvider.getString(R.string.signup_validname)))
            }

            state.hobby.isBlank() -> {
                postSideEffect(SignupSideEffect.showSnackbar(resourceProvider.getString(R.string.signup_validhobby)))
            }

            else -> {
                setUserData(
                    UserModel(
                        state.id,
                        state.password,
                        state.name,
                        state.hobby
                    )
                )
            }
        }
    }

    private fun setUserData(user: UserModel) = intent {
        runCatching { userDataRepository.setUserData(user.toUser()) }
            .onSuccess {
                postSideEffect(
                    SignupSideEffect.SignupSuccess
                )
            }
    }

    fun updateId(id: String) = updateState { copy(id = id) }
    fun updatePw(pw: String) = updateState { copy(password = pw) }
    fun updateName(name: String) = updateState { copy(name = name) }
    fun updateHobby(hobby: String) = updateState { copy(hobby = hobby) }
}