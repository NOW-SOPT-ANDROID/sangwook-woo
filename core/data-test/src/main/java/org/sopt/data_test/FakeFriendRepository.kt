package org.sopt.data_test

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.sopt.domain.repo.SoptRepository
import org.sopt.model.Friend

class FakeFriendRepository : SoptRepository {
    val friendList: MutableList<Friend> = mutableListOf()
    override fun getAll(): Flow<List<Friend>> = flow {
        emit(friendList)
    }

    override fun getContainInput(input: String): Flow<List<Friend>> = flow {
        emit(friendList.filter { it.name.contains(input) })
    }

    override suspend fun addFriend(friend: Friend) {
        friendList.add(friend)
    }

    override suspend fun deleteFriendById(id: Int) {
        friendList.removeIf { it.id == id }
    }

    override suspend fun deleteAll() {
        friendList.clear()
    }
}