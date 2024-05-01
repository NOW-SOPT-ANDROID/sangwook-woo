package org.sopt.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowLog
import org.sopt.common.security.SecurityInterface
import org.sopt.common.security.SecurityUtil
import org.sopt.common.security.fake.FakeAndroidKeyStoreProvider
import org.sopt.datastore.source.UserPreferencesDataSource

@RunWith(RobolectricTestRunner::class)
class DataStoreTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher + Job())
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private lateinit var securityUtil: SecurityInterface
    private lateinit var testDataStore: DataStore<UserData>
    private lateinit var userDataStore: UserPreferencesDataSource

    @Before
    fun setup() {
        ShadowLog.stream = System.out
        FakeAndroidKeyStoreProvider.setup()
        securityUtil = SecurityUtil()
        testDataStore = DataStoreFactory.create(
            serializer = UserDataSerializer(securityUtil),
            scope = testScope
        ){
            context.dataStoreFile("test_user_preferences.pb")
        }
        userDataStore = UserPreferencesDataSource(testDataStore)
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
        val (id, pw, name, hobby) = userDataStore.userData.first()
        assertTrue(id.isNullOrBlank() && pw.isNullOrBlank() && name.isNullOrBlank() && hobby.isNullOrBlank())
    }

    @Test
    fun afterSetUserData() = testScope.runTest {
        val user = UserData("testId", "testPw", "testName", "testHobby", false)
        userDataStore.setUserData(user)
        assertEquals(user, userDataStore.userData.first())
    }
}