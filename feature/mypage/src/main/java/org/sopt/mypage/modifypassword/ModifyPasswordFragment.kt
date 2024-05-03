package org.sopt.mypage.modifypassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import org.sopt.mypage.databinding.FragmentModifyPasswordBinding
import org.sopt.ui.fragment.snackBar

@AndroidEntryPoint
class ModifyPasswordFragment : Fragment() {
    private var _binding: FragmentModifyPasswordBinding? = null
    private val binding: FragmentModifyPasswordBinding
        get() = requireNotNull(_binding)

    private val viewModel by viewModels<ModifyPasswordViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentModifyPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initValueChangedListener()
        modifyPassword()
        collectState()
    }

    private fun collectState() {
        viewModel.observe(viewLifecycleOwner, sideEffect = ::handleSideEffect)
    }

    private fun handleSideEffect(sideEffect: ModifyPasswordSideEffect) {
        when (sideEffect) {
            ModifyPasswordSideEffect.ModifySuccess -> {
                findNavController().navigateUp()
            }

            is ModifyPasswordSideEffect.ShowSnackbar -> {
                snackBar(binding.root) { sideEffect.msg }
            }
        }
    }

    private fun modifyPassword() {
        binding.btnModifyPassword.setOnClickListener {
            viewModel.modifyPassword()
        }
    }

    private fun initValueChangedListener() {
        binding.etModifyPrevious.doAfterTextChanged {
            viewModel.updatePreviousPassword(it.toString())
        }
        binding.etModifyNew.doAfterTextChanged {
            viewModel.updateNewPassword(it.toString())
        }
        binding.etModifyVerification.doAfterTextChanged {
            viewModel.updateNewPasswordVerification(it.toString())
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}