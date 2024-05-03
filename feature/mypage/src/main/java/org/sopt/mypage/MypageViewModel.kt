package org.sopt.mypage

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.domain.repo.AuthRepository
import javax.inject.Inject

@HiltViewModel
class MypageViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ContainerHost<MypageState, MypageSideEffect>, ViewModel() {
    override val container: Container<MypageState, MypageSideEffect> = container(MypageState())

    init {
        getUserInfo()
    }

    private fun getUserInfo() = intent {
        authRepository.getUserinfo().onSuccess {
            if (it == null) return@intent
            reduce { state.copy(nickname = it.nickname, phone = it.phone) }
        }
    }

    fun logout() = intent {
        postSideEffect(MypageSideEffect.LogoutSuccess)
    }

    fun modifyPassword() = intent {
        postSideEffect(MypageSideEffect.NavigateModifyPassword)
    }
}