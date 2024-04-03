package org.sopt.main.home

import org.sopt.main.model.User

data class HomeState(
    val id: String = "",
    val password: String = "",
    val name: String = "",
    val hobby: String = ""
)

sealed interface HomeSideEffect {
    data object LoginSuccess : HomeSideEffect
}