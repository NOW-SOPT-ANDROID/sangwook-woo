package org.sopt.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.data.repository.AuthRepositoryImpl
import org.sopt.data.repository.FriendRepository
import org.sopt.data.repository.UserDataRepositoryImpl
import org.sopt.data.repository.UserRepositoryImpl
import org.sopt.domain.repo.AuthRepository
import org.sopt.domain.repo.SoptRepository
import org.sopt.domain.repo.UserDataRepository
import org.sopt.domain.repo.UserRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindsFriendRepo(soptRepository: FriendRepository): SoptRepository

    @Binds
    abstract fun bindsUserRepo(userDataRepository: UserDataRepositoryImpl): UserDataRepository

    @Binds
    abstract fun bindsMemberRepo(userRepository: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun authRepo(authRepository: AuthRepositoryImpl): AuthRepository
}