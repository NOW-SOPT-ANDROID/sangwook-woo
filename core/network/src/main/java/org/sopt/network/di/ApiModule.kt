package org.sopt.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.network.api.AuthApi
import org.sopt.network.api.ReqresApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideAuthService(@Auth retrofit: Retrofit): AuthApi =
        retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideReqresService(@Reqres retrofit: Retrofit): ReqresApi =
        retrofit.create(ReqresApi::class.java)
}