package org.sopt.mypage.modifypassword

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.domain.repo.AuthRepository
import org.sopt.model.exception.ApiError
import org.sopt.ui.orbit.updateState
import javax.inject.Inject

@HiltViewModel
class ModifyPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ContainerHost<ModifyPasswordState, ModifyPasswordSideEffect>, ViewModel() {
    override val container: Container<ModifyPasswordState, ModifyPasswordSideEffect> = container(
        ModifyPasswordState()
    )

    fun modifyPassword() = intent {
        authRepository.patchPassword(
            state.previousPassword,
            state.newPassword,
            state.newPasswordVerification
        ).onSuccess {
            postSideEffect(ModifyPasswordSideEffect.ModifySuccess)
        }.onFailure {
            if (it is ApiError) {
                postSideEffect(ModifyPasswordSideEffect.ShowSnackbar(it.message))
            } else {
                postSideEffect(ModifyPasswordSideEffect.ShowSnackbar("변경 실패"))
            }
        }
    }

    fun updatePreviousPassword(query: String) = updateState {
        copy(previousPassword = query)
    }

    fun updateNewPassword(query: String) = updateState {
        copy(newPassword = query)
    }

    fun updateNewPasswordVerification(query: String) = updateState {
        copy(newPasswordVerification = query)
    }

}