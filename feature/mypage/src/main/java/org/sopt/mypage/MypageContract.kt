package org.sopt.mypage

data class MypageState(
    val name: String = "",
    val hobby: String = "",
)

sealed interface MypageSideEffect{
    data object LogoutSuccess: MypageSideEffect

    data object WithdrawSuccess: MypageSideEffect
}