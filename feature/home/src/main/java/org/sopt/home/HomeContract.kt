package org.sopt.home

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.sopt.model.Friend

data class HomeState(
    val friendList: ImmutableList<Friend> = persistentListOf(),
    val query: String = "",
    val registeredHobby: String = "",
    val registeredName: String = "",
    val showBottomSheet: Boolean = false,
    val showDeleteDialog: Pair<Boolean, Int> = Pair(false, 0),
    val savingName: String = "",
    val savingHobby: String = "",
)

sealed interface HomeSideEffect