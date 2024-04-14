package org.sopt.datastore.source

import androidx.datastore.core.DataStore
import org.sopt.datastore.UserData
import java.io.IOException
import javax.inject.Inject

class UserPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserData>,
) {
    val userData = userPreferences.data

    suspend fun setUserData(user: UserData) {
        try {
            userPreferences.updateData {
                it.copy(user.id, user.pw, user.name, user.hobby)
            }
        } catch (ioException: IOException) {
            throw ioException
        }
    }

    suspend fun setAutoLogin(isChecked: Boolean) {
        try {
            userPreferences.updateData {
                it.copy(isAutoLogin = isChecked)
            }
        } catch (ioException: IOException) {
            throw ioException
        }
    }

    suspend fun deleteUser() {
        try {
            userPreferences.updateData {
                it.copy("","","","",false)
            }
        } catch (ioException: IOException) {
            throw ioException
        }
    }
}