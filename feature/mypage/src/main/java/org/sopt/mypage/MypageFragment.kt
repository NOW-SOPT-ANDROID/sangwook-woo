package org.sopt.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import org.sopt.common.intentprovider.IntentProvider
import org.sopt.common.intentprovider.qualifier.LOGIN
import org.sopt.mypage.databinding.FragmentMypageBinding
import javax.inject.Inject

@AndroidEntryPoint
class MypageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding
        get() = requireNotNull(_binding)
    private val viewModel by viewModels<MypageViewModel>()

    @Inject
    @LOGIN
    lateinit var intentProvider: IntentProvider

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectState()
        initBtnClickListener()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initBtnClickListener() {
        binding.btnMypageLogout.setOnClickListener {
            viewModel.logout()
        }

        binding.btnMypageWithdraw.setOnClickListener {
            viewModel.signout()
        }
    }

    private fun collectState() {
        viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)
    }

    private fun render(mypageState: MypageState) {
        binding.tvMypageName.text = mypageState.name
        binding.tvMypageHobby.text = mypageState.hobby
    }

    private fun handleSideEffect(sideEffect: MypageSideEffect) {
        when (sideEffect) {
            MypageSideEffect.LogoutSuccess -> {
                startActivity(intentProvider.getIntent())
                requireActivity().finish()
            }

            MypageSideEffect.WithdrawSuccess -> {
                startActivity(intentProvider.getIntent())
                requireActivity().finish()
            }
        }
    }
}