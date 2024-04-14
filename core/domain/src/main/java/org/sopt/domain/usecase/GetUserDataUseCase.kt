package org.sopt.domain.usecase

import org.sopt.domain.repo.UserDataRepository
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository
) {
    operator fun invoke() = userDataRepository.getUserData()
}