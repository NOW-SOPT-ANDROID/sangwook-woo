package org.sopt.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.sopt.domain.repo.UserRepository
import org.sopt.model.ReqresUser
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _userState: MutableStateFlow<PagingData<ReqresUser>> =
        MutableStateFlow(PagingData.empty())
    val userState: StateFlow<PagingData<ReqresUser>> = _userState.asStateFlow()

    init {
        viewModelScope.launch {
            getUsers()
        }
    }

    private suspend fun getUsers() {
        userRepository.getUser()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .catch { t ->
                Log.e("error", t.message.toString())
            }
            .collectLatest {
                _userState.emit(it)
            }
    }
}