package org.sopt.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.debounce
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.domain.repo.SoptRepository
import org.sopt.domain.usecase.GetSoptUseCase
import org.sopt.domain.usecase.GetUserDataUseCase
import org.sopt.model.Friend
import org.sopt.ui.orbit.updateState
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSoptUseCase: GetSoptUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,
    private val soptRepository: SoptRepository,
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {
    override val container: Container<HomeState, HomeSideEffect> = container(HomeState())

    private var observeGetSoptJob: Job? = null

    init {
        getUserData()
        observeGetSoptJob = observeGetSopt()
        intent {
            container.stateFlow.debounce(300)
                .collect {
                    observeGetSoptJob?.cancel()
                    observeGetSoptJob = observeGetSopt()
                }
        }
    }

    private fun getUserData() = intent {
        getUserDataUseCase().collect {
            reduce {
                state.copy(
                    registeredHobby = it.hobby,
                    registeredName = it.name,
                )
            }
        }
    }

    private fun observeGetSopt() = intent {
        getSoptUseCase(param = GetSoptUseCase.Param(state.query)).collect {
            reduce { state.copy(friendList = it) }
        }
    }

    fun insertFriend() = intent {
        val friend = Friend(null, state.savingName, state.savingHobby)
        runCatching { soptRepository.addFriend(friend) }
            .onSuccess {
                reduce { state.copy(showBottomSheet = false, savingName = "", savingHobby = "") }
            }
    }


    fun updateQuery(query: String) = updateState {
        copy(query = query)
    }

    fun updateSavingName(name: String) = updateState {
        copy(savingName = name)
    }

    fun updateSavingHobby(hobby: String) = updateState {
        copy(savingHobby = hobby)
    }

    fun showAddFriendBottomSheet() = updateState {
        copy(showBottomSheet = true)
    }

    fun dismissAddFriendBottomSheet() = updateState {
        copy(showBottomSheet = false)
    }

    fun showDeleteDialog(id: Int) = updateState {
        copy(showDeleteDialog = Pair(true, id))
    }

    fun dismissDeleteDialog() = updateState { copy(showDeleteDialog = Pair(false, 0)) }

    fun deleteFriend(id: Int) = intent {
        soptRepository.deleteFriendById(id)
        dismissDeleteDialog()
    }
}