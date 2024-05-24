package org.sopt.main.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import org.sopt.designsystem.R
import org.sopt.main.databinding.ActivityLoginBinding
import org.sopt.main.main.MainActivity
import org.sopt.main.signup.SignupActivity
import org.sopt.ui.context.snackBar
import org.sopt.ui.context.stringOf

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSignupButtonClickListener()
        initLoginButtonClickListener()
        collectState()
        initValueChangedListener()
    }

    private fun initValueChangedListener() {
        binding.etLoginId.doAfterTextChanged {
            viewModel.updateId(it.toString())
        }

        binding.etLoginPw.doAfterTextChanged {
            viewModel.updatePw(it.toString())
        }
    }

    private fun initSignupButtonClickListener() {
        binding.btnLoginSignup.setOnClickListener {
            viewModel.signup()
        }
    }

    private fun initLoginButtonClickListener() {
        binding.btnLoginLogin.setOnClickListener {
            viewModel.login()
        }
    }

    private fun collectState() {
        viewModel.observe(lifecycleOwner = this, sideEffect = ::handleSideEffect)
    }

    private fun handleSideEffect(sideEffect: LoginSideEffect) {
        when (sideEffect) {
            LoginSideEffect.NavigateToSignUp -> {
                startActivity(Intent(this, SignupActivity::class.java))
            }

            LoginSideEffect.LoginSuccess -> {
                startActivity(MainActivity.newInstance(this))
            }

            LoginSideEffect.SignupSuccess -> {
                snackBar(binding.root) { stringOf(R.string.login_signup_success) }
            }

            is LoginSideEffect.showSnackbar -> {
                snackBar(binding.root) { sideEffect.message }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context) = Intent(context, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }
}