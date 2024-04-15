package org.sopt.domain_test

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.sopt.data_test.FakeFriendRepository
import org.sopt.domain.usecase.GetSoptUseCase
import org.sopt.model.Friend

class UseCaseTest {
    lateinit var getSoptUseCase: GetSoptUseCase
    private lateinit var soptRepository: FakeFriendRepository

    private val mockFriendList = listOf(
        Friend(
            id = 1,
            name = "test1",
            hobby = "test1",
        ),
        Friend(
            id = 2,
            name = "test2",
            hobby = "test2",
        ),
        Friend(
            id = 3,
            name = "test3",
            hobby = "test3",
        ),
        Friend(
            id = 4,
            name = "test4",
            hobby = "test4",
        ),
    )
    @Before
    fun setup() {
        soptRepository = FakeFriendRepository()

        runBlocking {
            mockFriendList.forEach {
                soptRepository.addFriend(it)
            }
        }

        getSoptUseCase = GetSoptUseCase(soptRepository)
    }

    @Test
    fun emptyQueryUseCaseTest() {
        val input = ""
        val userList = runBlocking { getSoptUseCase( param = GetSoptUseCase.Param(input)).first() }
        assertEquals(mockFriendList, userList)
    }

    @Test
    fun inputQueryUseCaseTest() {
        val input = "test2"
        val userList = runBlocking { getSoptUseCase( param = GetSoptUseCase.Param(input)).first() }
        assertEquals(mockFriendList.first { it.name.contains(input) }, userList.first())
    }
}