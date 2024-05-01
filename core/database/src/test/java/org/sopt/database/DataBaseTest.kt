package org.sopt.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.sopt.database.entity.SoptEntity

@RunWith(RobolectricTestRunner::class)
class DataBaseTest {
    private lateinit var soptDao: SoptDao
    private lateinit var db: SoptDataBase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            SoptDataBase::class.java,
        ).build()
        soptDao = db.soptDao()
    }

    @After
    fun closeDb() = db.close()

    @Test
    fun saveUserTest() = runTest {
        val mockFriendList = listOf(
            SoptEntity(
                id = 1,
                name = "name1",
                hobby = "hobby1",
            ),
            SoptEntity(
                id = 2,
                name = "name2",
                hobby = "hobby2",
            ),
            SoptEntity(
                id = 3,
                name = "name3",
                hobby = "hobby3",
            ),
        )

        soptDao.addFriends(mockFriendList)
        val savedFriends = soptDao.getAll().first()
        assertEquals(mockFriendList, savedFriends)
    }

    @Test
    fun findByInputTest() = runTest {
        val mockFriendList = listOf(
            SoptEntity(
                id = 1,
                name = "nameTest1",
                hobby = "hobby1",
            ),
            SoptEntity(
                id = 2,
                name = "nameTest2",
                hobby = "hobby2",
            ),
            SoptEntity(
                id = 3,
                name = "nameTest3",
                hobby = "hobby3",
            ),
        )
        val input = "Test2"
        soptDao.addFriends(mockFriendList)
        val findedFriends = soptDao.getContainInput(input).first()
        assertEquals(mockFriendList[1], findedFriends.first())
    }

    @Test
    fun generateIdTest() = runTest {
        val mockFriend = SoptEntity(
            id = null,
            name = "test",
            hobby = "test",
        )
        soptDao.addFriend(mockFriend)
        val savedFriend = soptDao.getAll().first().first()
        assertEquals(mockFriend.copy(id = 1), savedFriend)
    }
}