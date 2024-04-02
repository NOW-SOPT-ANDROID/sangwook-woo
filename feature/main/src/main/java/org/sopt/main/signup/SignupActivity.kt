package org.sopt.main.signup

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import org.sopt.main.const.IntentKey.USER_KEY
import org.sopt.main.databinding.ActivitySignupBinding
import org.sopt.ui.context.snackBar

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val viewModel by viewModels<SignupViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEdittextListener()
        initSignupButtonClickListener()
        collectState()
    }
    private fun initEdittextListener() {
        binding.apply {
            etSignupId.doAfterTextChanged { viewModel.updateId(it.toString()) }
            etSignupPw.doAfterTextChanged { viewModel.updatePw(it.toString()) }
            etSignupName.doAfterTextChanged { viewModel.updateName(it.toString()) }
            etSignupHobby.doAfterTextChanged { viewModel.updateHobby(it.toString()) }
        }
    }

    private fun initSignupButtonClickListener() {
        binding.btnSignupSignup.setOnClickListener {
            viewModel.signup()
        }
    }

    private fun collectState() {
        viewModel.observe(this, sideEffect = ::handleSideEffect)
    }

    private fun handleSideEffect(sideEffect: SignupSideEffect) {
        when (sideEffect) {
            is SignupSideEffect.SignupSuccess -> {
                intent.putExtra(USER_KEY, sideEffect.user).apply {
                    setResult(RESULT_OK, this)
                }
                finish()
            }

            is SignupSideEffect.showSnackbar -> {
                snackBar(binding.root) { sideEffect.message }
            }
        }
    }
}