package org.sopt.domain.repo

import kotlinx.coroutines.flow.Flow
import org.sopt.model.User

interface UserDataRepository {

    fun getUserData(): Flow<User>

    suspend fun setUserData(user: User)

    suspend fun setAutoLogin(isChecked: Boolean)

    suspend fun deleteUserData()
}