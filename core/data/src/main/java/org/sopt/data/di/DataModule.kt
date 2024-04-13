package org.sopt.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.data.repository.FriendRepository
import org.sopt.domain.repo.SoptRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun bindsFriendRepo(soptRepository: FriendRepository): SoptRepository
}