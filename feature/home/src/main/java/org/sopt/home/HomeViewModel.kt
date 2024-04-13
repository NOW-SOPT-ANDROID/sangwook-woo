package org.sopt.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.domain.repo.SoptRepository
import org.sopt.domain.usecase.GetSoptUseCase
import org.sopt.model.Friend
import org.sopt.ui.orbit.updateState
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSoptUseCase: GetSoptUseCase,
    private val soptRepository: SoptRepository,
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {
    override val container: Container<HomeState, HomeSideEffect> = container(HomeState())


    init {
        observeGetSopt()

        viewModelScope.launch {
            container.stateFlow.debounce(300)
                .filter { it.query.isNotEmpty() || it.query.isBlank() }
                .collect {
                    observeGetSopt()
                }
        }
    }

    private fun observeGetSopt() = intent {
        viewModelScope.launch {
            getSoptUseCase(param = GetSoptUseCase.Param(state.query)).collect {
                reduce { state.copy(friendList = it) }
            }
        }
    }

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
        Log.e("이벤트", id.toString())
        if (id != null) {
            postSideEffect(HomeSideEffect.showDeleteDialog(id))
        }
    }

    fun deleteFriend(id: Int) = viewModelScope.launch {
        soptRepository.deleteFriendById(id)
    }
}