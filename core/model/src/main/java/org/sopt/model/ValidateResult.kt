package org.sopt.model

sealed class ValidateResult {
    data object EmptyError : ValidateResult()
    data object Error : ValidateResult()
    data object Success : ValidateResult()

    companion object{
        fun getResult(isValid: Boolean) = if (isValid) Success else Error
    }
}