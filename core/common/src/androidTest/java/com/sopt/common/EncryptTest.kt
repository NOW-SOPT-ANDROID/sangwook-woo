package com.sopt.common

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.sopt.common.security.SecurityUtil

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class EncryptTest {
    lateinit var securityUtil : SecurityUtil
    val testKey = "test-key"

    @Before
    fun setup() {
        securityUtil = SecurityUtil()
    }

    @Test
    fun encryptTest() {
        val testMessage = "testMessage"
        val (encryptedData, iv) = securityUtil.encryptData(testKey,testMessage)
        val decryptedData = securityUtil.decryptData(testKey, encryptedData, iv)

        assertEquals(testMessage, decryptedData.decodeToString())
    }
}