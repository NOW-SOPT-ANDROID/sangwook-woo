package org.sopt.main.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.common.intentprovider.IntentProvider
import org.sopt.common.intentprovider.qualifier.LOGIN
import org.sopt.common.intentprovider.qualifier.MAIN
import org.sopt.common.intentprovider.qualifier.SIGNUP
import org.sopt.main.intentprovider.LoginIntentProvider
import org.sopt.main.intentprovider.MainIntentProvider
import org.sopt.main.intentprovider.SignupIntentProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class IntentModule {
    @Singleton
    @Binds
    @LOGIN
    abstract fun bindsLoginIntent(intentProvider: LoginIntentProvider): IntentProvider

    @Singleton
    @Binds
    @MAIN
    abstract fun bindsMainIntent(intentProvider: MainIntentProvider): IntentProvider

    @Singleton
    @Binds
    @SIGNUP
    abstract fun bindsSignupIntent(intentProvider: SignupIntentProvider): IntentProvider
}