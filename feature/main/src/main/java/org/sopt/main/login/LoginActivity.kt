package org.sopt.main.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import org.sopt.designsystem.R
import org.sopt.main.const.IntentKey.USER_KEY
import org.sopt.main.databinding.ActivityLoginBinding
import org.sopt.main.main.MainActivity
import org.sopt.main.model.User
import org.sopt.main.signup.SignupActivity
import org.sopt.ui.context.snackBar
import org.sopt.ui.context.stringOf
import org.sopt.ui.intent.getParcelable

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initResultLauncher()
        initSignupButtonClickListener()
        initLoginButtonClickListener()
        collectState()
    }

    private fun initResultLauncher() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.getParcelable(USER_KEY, User::class.java).let { user ->
                    viewModel.signupSuccess(user)
                }
            }
        }
    }

    private fun initSignupButtonClickListener() {
        binding.btnLoginSignup.setOnClickListener {
            viewModel.signup()
        }
    }

    private fun initLoginButtonClickListener() {
        binding.btnLoginLogin.setOnClickListener {
            viewModel.login(binding.etLoginId.text.toString(), binding.etLoginPw.text.toString())
        }
    }

    private fun collectState() {
        viewModel.observe(lifecycleOwner = this, sideEffect = ::handleSideEffect)
    }

    private fun handleSideEffect(sideEffect: LoginSideEffect) {
        when (sideEffect) {
            is LoginSideEffect.NavigateToSignUp -> {
                Intent(this, SignupActivity::class.java).apply {
                    resultLauncher.launch(this)
                }
            }

            is LoginSideEffect.LoginSuccess -> {
                Intent(this, MainActivity::class.java).apply {
                    putExtra(USER_KEY, sideEffect.user)
                    startActivity(this)
                }
            }
            LoginSideEffect.SignupSuccess -> {
                snackBar(binding.root) { stringOf(R.string.login_signup_success) }
            }
            is LoginSideEffect.showSnackbar -> {
                snackBar(binding.root) { sideEffect.message }
            }
        }
    }
}