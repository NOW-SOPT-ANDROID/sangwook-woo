package org.sopt.data.repository

import kotlinx.coroutines.flow.map
import org.sopt.data.model.toFriend
import org.sopt.database.SoptDao
import org.sopt.database.entity.SoptEntity
import org.sopt.domain.repo.SoptRepository
import org.sopt.model.Friend
import javax.inject.Inject

class FriendRepository @Inject constructor(
    private val soptDao: SoptDao
): SoptRepository {
    override fun getAll() = soptDao.getAll()
        .map{ it.map(SoptEntity::toFriend) }

    override fun getContainInput(input: String) = soptDao.getContainInput(input)
        .map { it.map(SoptEntity::toFriend) }

    override suspend fun addFriend(friend: Friend) = soptDao.addFriend(
        SoptEntity(
            id = friend.id,
            name = friend.name,
            hobby = friend.hobby,
        )
    )

    override suspend fun deleteFriendById(id: Int) = soptDao.deleteFriendById(id)

    override suspend fun deleteAll() = soptDao.deleteAll()
}