package org.sopt.domain.usecase

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import org.sopt.domain.repo.SoptRepository
import javax.inject.Inject

class GetSoptUseCase @Inject constructor(
    private val soptRepository: SoptRepository,
) {
    @OptIn(FlowPreview::class)
    operator fun invoke(param: Param) = if (param.query.isBlank()) {
        soptRepository.getAll()
    } else {
        soptRepository.getContainInput(param.query)
            .debounce(400)
    }


    data class Param(
        val query: String,
    )
}