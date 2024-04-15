package org.sopt.main.intentprovider

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import org.sopt.common.intentprovider.IntentProvider
import org.sopt.main.signup.SignupActivity
import javax.inject.Inject

class SignupIntentProvider @Inject constructor(
    @ApplicationContext private val context: Context,
) : IntentProvider {
    override fun getIntent() = SignupActivity.newInstance(context)
}