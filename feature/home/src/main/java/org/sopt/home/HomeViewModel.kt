package org.sopt.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.domain.repo.SoptRepository
import org.sopt.domain.usecase.GetSoptUseCase
import org.sopt.domain.usecase.GetUserDataUseCase
import org.sopt.model.Friend
import org.sopt.ui.orbit.updateState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSoptUseCase: GetSoptUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,
    private val soptRepository: SoptRepository,
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {
    override val container: Container<HomeState, HomeSideEffect> = container(HomeState())

    init {
        getUserData()
        intent {
            container.stateFlow
                .flatMapLatest {
                    getSopt(it.query)
                }
                .collect {
                    reduce {
                        state.copy(
                            friendList = (listOf(
                                Friend(
                                    id = 0,
                                    name = state.registeredName,
                                    hobby = state.registeredHobby
                                )
                            ) + it).toImmutableList()
                        )
                    }
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

    private fun getSopt(query: String) = getSoptUseCase(param = GetSoptUseCase.Param(query = query))


    fun addFriend() = intent {
        postSideEffect(HomeSideEffect.showAddFriendBottomSheet)
    }

    fun insertFriend(friend: Friend) = viewModelScope.launch {
        soptRepository.addFriend(friend)
    }

    fun updateQuery(query: String) = updateState {
        copy(query = query)
    }

    fun showDeleteDialog(id: Int?) = intent {
        if (id != null) {
            postSideEffect(HomeSideEffect.showDeleteDialog(id))
        }
    }

    fun deleteFriend(id: Int) = viewModelScope.launch {
        soptRepository.deleteFriendById(id)
    }
}