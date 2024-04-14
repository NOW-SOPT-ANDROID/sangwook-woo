package org.sopt.data.repository

import kotlinx.coroutines.flow.map
import org.sopt.data.model.toUser
import org.sopt.datastore.UserData
import org.sopt.datastore.source.UserPreferencesDataSource
import org.sopt.domain.repo.UserDataRepository
import org.sopt.model.User
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val userPreferencesDataSource: UserPreferencesDataSource
): UserDataRepository {
    override fun getUserData() = userPreferencesDataSource.userData.map {
        it.toUser()
    }

    override suspend fun setUserData(user: User) = userPreferencesDataSource.setUserData(
        UserData(
            id = user.id,
            pw = user.pw,
            name = user.name,
            hobby = user.hobby,
        )
    )

    override suspend fun setAutoLogin(isChecked: Boolean) = userPreferencesDataSource.setAutoLogin(isChecked)

    override suspend fun deleteUserData() = userPreferencesDataSource.deleteUser()
}