package org.sopt.domain.repo

import kotlinx.coroutines.flow.Flow
import org.sopt.model.Friend

interface SoptRepository {
    fun getAll(): Flow<List<Friend>>

    fun getContainInput(input: String): Flow<List<Friend>>

    suspend fun addFriend(friend: Friend)

    suspend fun deleteFriendById(id: Int)

    suspend fun deleteAll()
}