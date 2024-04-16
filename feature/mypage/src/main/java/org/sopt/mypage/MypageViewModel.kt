package org.sopt.mypage

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.domain.repo.UserDataRepository
import org.sopt.model.User
import javax.inject.Inject

@HiltViewModel
class MypageViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
) : ContainerHost<MypageState, MypageSideEffect>, ViewModel() {
    override val container: Container<MypageState, MypageSideEffect> = container(MypageState())

    init {
        getUserData()
    }

    private fun getUserData() = intent {
        userDataRepository.getUserData().collect {
            reduce { state.copy(name = it.name, hobby = it.hobby) }
        }
    }

    fun logout() = intent {
        setAutoLoginFalse()
            .onSuccess { postSideEffect(MypageSideEffect.LogoutSuccess) }
    }

    fun signout() = intent {
        deleteUserData()
            .onSuccess { postSideEffect(MypageSideEffect.WithdrawSuccess) }
    }

    private suspend fun setAutoLoginFalse() =
        runCatching { userDataRepository.setAutoLogin(false) }

    private suspend fun deleteUserData() =
        runCatching { userDataRepository.deleteUserData() }

}