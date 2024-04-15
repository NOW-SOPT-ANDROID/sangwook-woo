package org.sopt.main.intentprovider

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import org.sopt.common.intentprovider.IntentProvider
import org.sopt.main.login.LoginActivity
import javax.inject.Inject

class LoginIntentProvider @Inject constructor(
    @ApplicationContext private val context: Context,
) : IntentProvider {
    override fun getIntent() = LoginActivity.newInstance(context)
}