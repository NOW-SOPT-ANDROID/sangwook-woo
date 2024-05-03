package org.sopt.mypage

data class MypageState(
    val nickname: String = "",
    val phone: String = "",
)

sealed interface MypageSideEffect {
    data object LogoutSuccess : MypageSideEffect

    data object NavigateModifyPassword : MypageSideEffect
}