package org.sopt.main.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() :
    ContainerHost<MainState, MainSideEffect>, ViewModel() {
    override val container: Container<MainState, MainSideEffect> = container(MainState())

    fun updateBnvVisible(isVisible: Boolean) = intent {
        reduce {
            state.copy(isBnvBarVisible = isVisible)
        }
    }
}