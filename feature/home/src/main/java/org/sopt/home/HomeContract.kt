package org.sopt.home

import org.sopt.model.Friend

data class HomeState(
    val friendList: List<Friend> = emptyList(),
    val query: String = "",
)

sealed interface HomeSideEffect{
    data class showDeleteDialog(val id: Int): HomeSideEffect
    data object showAddFriendBottomSheet: HomeSideEffect
}