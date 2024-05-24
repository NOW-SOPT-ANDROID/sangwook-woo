package org.sopt.network.interceptor

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.sopt.datastore.source.UserPreferencesDataSource
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val userPreferencesDataSource: UserPreferencesDataSource,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val authResponse = chain.proceed(handleRequest(chain.request()))
        val memberId = authResponse.header("Location")?.toInt()
        runBlocking { if (memberId != null) userPreferencesDataSource.setMemberId(memberId) }

        return authResponse
    }

    private fun handleRequest(originalRequest: Request) =
        originalRequest.newBuilder().addHeader("memberId",
            runBlocking { userPreferencesDataSource.userData.first().memberId.toString() }
        ).build()
}