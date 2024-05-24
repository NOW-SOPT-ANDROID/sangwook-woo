package org.sopt.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.home.adapter.HomeAdapter
import org.sopt.home.databinding.FragmentHomeBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.fragment.viewLifeCycle
import org.sopt.ui.fragment.viewLifeCycleScope

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>({ FragmentHomeBinding.inflate(it) }) {
    private lateinit var homeAdapter: HomeAdapter
    private val viewModel by viewModels<HomeViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initHomeAdapter()
        initUserListStateObserver()
    }

    private fun initHomeAdapter() {
        homeAdapter = HomeAdapter(
        )
        binding.rvHome.adapter = homeAdapter
    }

    private fun initUserListStateObserver() {
        viewModel.userState.flowWithLifecycle(viewLifeCycle).onEach { state ->
            homeAdapter.submitData(state)
        }.launchIn(viewLifeCycleScope)
    }

}