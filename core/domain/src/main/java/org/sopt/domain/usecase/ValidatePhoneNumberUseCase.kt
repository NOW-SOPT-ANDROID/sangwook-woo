package org.sopt.domain.usecase

import org.sopt.model.ValidateResult
import org.sopt.model.getResult
import javax.inject.Inject

class ValidatePhoneNumberUseCase @Inject constructor() {
    operator fun invoke(phone: String): ValidateResult {
        if (phone.isBlank()) return ValidateResult.EmptyError
        return getResult(phoneNumberPattern.matches(phone))
    }

    companion object {
        val phoneNumberPattern = Regex(
            """^01[0-9]-[0-9]{3,4}-[0-9]{4}$"""
        )
    }
}