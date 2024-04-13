package org.sopt.main.main

data class MainState(
    val isBnvBarVisible: Boolean = true,
)

sealed interface MainSideEffect {
}