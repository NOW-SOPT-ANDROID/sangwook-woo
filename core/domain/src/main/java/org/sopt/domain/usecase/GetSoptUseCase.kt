package org.sopt.domain.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import org.sopt.domain.repo.SoptRepository
import javax.inject.Inject

class GetSoptUseCase @Inject constructor(
    private val soptRepository: SoptRepository,
) {
    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    operator fun invoke(param: Param) = if (param.query.isBlank()) {
        soptRepository.getAll()
    } else {
        soptRepository.getContainInput(param.query)
            .debounce(200)
            .flatMapLatest {
                flow {
                    emit(it)
                }
            }
    }


    data class Param(
        val query: String,
    )
}