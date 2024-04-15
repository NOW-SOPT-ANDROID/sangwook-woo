package com.sopt.common

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.sopt.common.security.SecurityUtil

@RunWith(AndroidJUnit4::class)
class EncryptTest {
    lateinit var securityUtil: SecurityUtil
    val testKey = "test-key"

    @Before
    fun setup() {
        securityUtil = SecurityUtil()
    }

    @Test
    fun encryptTest() {
        val testMessage = "testMessage"
        val (encryptedData, iv) = securityUtil.encryptData(testKey, testMessage)
        val decryptedData = securityUtil.decryptData(testKey, encryptedData, iv)

        assertEquals(testMessage, decryptedData.decodeToString())
    }
}