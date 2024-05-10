package org.sopt.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import org.sopt.domain.repo.UserRepository
import org.sopt.model.ReqresUser
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    val users: Flow<PagingData<ReqresUser>> =
        userRepository.getUser().distinctUntilChanged().cachedIn(viewModelScope)
}