package org.sopt.ui.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.ui.context.ResourceProvider
import org.sopt.ui.context.ResourceProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ResourceProviderModule {
    @Binds
    @Singleton
    abstract fun bindsResourceProvider(resourceProvider: ResourceProviderImpl): ResourceProvider
}