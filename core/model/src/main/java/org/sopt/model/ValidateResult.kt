package org.sopt.model

sealed class ValidateResult {
    data object EmptyError : ValidateResult()
    data object Error : ValidateResult()
    data object Success : ValidateResult()
}

fun getResult(isValid: Boolean) = if (isValid) ValidateResult.Success else ValidateResult.Error