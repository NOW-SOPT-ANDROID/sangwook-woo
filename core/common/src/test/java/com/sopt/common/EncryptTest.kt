package com.sopt.common

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.sopt.common.security.SecurityInterface
import org.sopt.common.security.SecurityUtil
import org.sopt.common.security.fake.FakeAndroidKeyStoreProvider

@RunWith(RobolectricTestRunner::class)
class EncryptTest {
    lateinit var securityUtil: SecurityInterface
    val testKey = "test-key"
    @Before
    fun setup() {
        FakeAndroidKeyStoreProvider.setup()
    }

    @Test
    fun encryptTest() {
        securityUtil = SecurityUtil()
        val testMessage = "testMessage"
        val (encryptedData, iv) = securityUtil.encryptData(testKey, testMessage)
        val decryptedData = securityUtil.decryptData(testKey, encryptedData, iv)

        assertEquals(testMessage, decryptedData.decodeToString())
    }
}