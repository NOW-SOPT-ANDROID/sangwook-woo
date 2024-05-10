package org.sopt.main.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.domain.repo.AuthRepository
import org.sopt.domain.usecase.ValidatePasswordUseCase
import org.sopt.domain.usecase.ValidatePhoneNumberUseCase
import org.sopt.model.Member
import org.sopt.model.ValidateResult
import org.sopt.model.exception.ApiError
import javax.inject.Inject

@OptIn(OrbitExperimental::class)
@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase,
) : ContainerHost<SignupState, SignupSideEffect>, ViewModel() {
    override val container: Container<SignupState, SignupSideEffect> = container(SignupState())

    fun signup() = intent {
        val member = Member(
            id = state.id,
            nickname = state.name,
            phone = state.phone
        )

        authRepository.postSignup(member, state.password)
            .onSuccess {
                postSideEffect(SignupSideEffect.SignupSuccess)
            }.onFailure {
                if(it is ApiError){
                    postSideEffect(SignupSideEffect.ShowSnackbar(it.message))
                }else{
                    postSideEffect(SignupSideEffect.ShowSnackbar("회원가입 실패"))
                }
            }
    }

    fun updateId(id: String) = blockingIntent {
        if (id.isBlank()) reduce { state.copy(id = id, idValidation = ValidateResult.EmptyError) }
        else reduce { state.copy(id = id, idValidation = ValidateResult.Success) }
    }

    fun updatePw(pw: String) = blockingIntent {
        reduce { state.copy(password = pw, passwordValidation = validatePasswordUseCase(pw)) }
    }

    fun updateName(name: String) = blockingIntent {
        if (name.isBlank()) reduce {
            state.copy(
                name = name,
                idValidation = ValidateResult.EmptyError
            )
        }
        else reduce { state.copy(name = name, nameValidation = ValidateResult.Success) }
    }

    fun updatePhone(phone: String) = blockingIntent {
        reduce { state.copy(phone = phone, phoneValidation = validatePhoneNumberUseCase(phone)) }
    }
}