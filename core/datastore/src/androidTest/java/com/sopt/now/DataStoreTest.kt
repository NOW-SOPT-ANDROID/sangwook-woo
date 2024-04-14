package com.sopt.now

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import org.sopt.common.security.SecurityInterface
import org.sopt.common.security.SecurityUtil
import org.sopt.datastore.UserData
import org.sopt.datastore.UserDataSerializer
import org.sopt.datastore.di.DataStoreTestModule.testUserDataStore
import org.sopt.datastore.source.UserPreferencesDataSource

@RunWith(AndroidJUnit4::class)
class DataStoreTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var userDataStore: UserPreferencesDataSource
    private lateinit var securityUtil: SecurityInterface
    @get:Rule
    val tempFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @Before
    fun setup() {
        securityUtil = SecurityUtil()

        userDataStore = UserPreferencesDataSource(
            tempFolder.testUserDataStore(UserDataSerializer(securityUtil), testScope)
        )
    }

    @Test
    fun autoLoginDefault() = testScope.runTest {
        assertFalse(userDataStore.userData.first().isAutoLogin)
    }

    @Test
    fun afterSetAutoLogin() = testScope.runTest {
        userDataStore.setAutoLogin(true)
        assertTrue(userDataStore.userData.first().isAutoLogin)
    }

    @Test
    fun userDefault() = testScope.runTest {
        val (id,pw,name,hobby) = userDataStore.userData.first()
        assertTrue(id.isNullOrBlank() && pw.isNullOrBlank() && name.isNullOrBlank() && hobby.isNullOrBlank())
    }

    @Test
    fun afterSetUserData() = testScope.runTest {
        val user = UserData("testId", "testPw", "testName", "testHobby", false)
        userDataStore.setUserData(user)
        assertEquals(user, userDataStore.userData.first())
    }
}