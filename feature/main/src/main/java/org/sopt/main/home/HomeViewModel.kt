package org.sopt.main.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.main.model.User
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): ContainerHost<HomeState, HomeSideEffect>, ViewModel() {
    override val container: Container<HomeState, HomeSideEffect> = container(HomeState())

    fun updateState(user: User) = intent {
        postSideEffect(HomeSideEffect.LoginSuccess)

        reduce {
            state.copy(
                id = user.id,
                password = user.pw,
                name = user.name,
                hobby = user.hobby
            )
        }
    }
}