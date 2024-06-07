package org.sopt.main.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
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
            etSignupHobby.doAfterTextChanged { viewModel.updatePhone(it.toString()) }
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
                finish()
            }

            is SignupSideEffect.ShowSnackbar -> {
                snackBar(binding.root) { sideEffect.message }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context) = Intent(context, SignupActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }
}