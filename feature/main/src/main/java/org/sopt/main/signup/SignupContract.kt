package org.sopt.main.signup

import org.sopt.model.ValidateResult

data class SignupState(
    val id: String = "",
    val password: String = "",
    val name: String = "",
    val phone: String = "",
    val passwordValidation: ValidateResult = ValidateResult.EmptyError,
    val idValidation: ValidateResult = ValidateResult.EmptyError,
    val nameValidation: ValidateResult = ValidateResult.EmptyError,
    val phoneValidation: ValidateResult = ValidateResult.EmptyError,
)

sealed interface SignupSideEffect {
    data class ShowSnackbar(val message: String) : SignupSideEffect
    data object SignupSuccess : SignupSideEffect
}