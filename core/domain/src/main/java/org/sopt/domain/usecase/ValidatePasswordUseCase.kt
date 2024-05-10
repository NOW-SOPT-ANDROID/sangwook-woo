package org.sopt.domain.usecase

import org.sopt.model.ValidateResult
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor() {
    operator fun invoke(password: String): ValidateResult {
        if (password.isBlank()) return ValidateResult.EmptyError
        return ValidateResult.getResult(passwordPattern.matches(password))
    }

    companion object {
        val passwordPattern = Regex(
            """^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()-_=+{};:,<>/~`|'"\[\]\\?\.]).{8,}$"""
        )
    }
}