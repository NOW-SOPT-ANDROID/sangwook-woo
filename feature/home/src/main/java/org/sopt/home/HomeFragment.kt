package org.sopt.home

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import org.sopt.designsystem.dialog.AlertDialogFragment
import org.sopt.home.adapter.HomeAdapter
import org.sopt.home.adapter.HomeHeaderAdapter
import org.sopt.home.add.AddFriendBottomSheetFragment
import org.sopt.home.databinding.FragmentHomeBinding
import org.sopt.model.Friend
import org.sopt.ui.base.BindingFragment

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>({ FragmentHomeBinding.inflate(it) }) {
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var homeHeaderAdapter: HomeHeaderAdapter
    private val viewModel by viewModels<HomeViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initListener()
        collectState()
    }

    private fun initListener() {
        binding.etHomeTopSearch.doAfterTextChanged {
            viewModel.updateQuery(it.toString())
        }
        binding.fabHome.setOnClickListener {
            viewModel.addFriend()
        }
    }

    private fun collectState() {
        viewModel.observe(lifecycleOwner = this, state = ::render, sideEffect = ::handleSideEffect)
    }

    private fun render(homeState: HomeState) {
        homeAdapter.submitList(homeState.friendList)
        viewModel.updateQuery(homeState.query)
    }

    private fun handleSideEffect(sideEffect: HomeSideEffect) {
        when (sideEffect) {
            HomeSideEffect.showAddFriendBottomSheet -> {
                AddFriendBottomSheetFragment.newInstance {
                    viewModel.insertFriend(it)
                }.show(parentFragmentManager, this.tag)
            }

            is HomeSideEffect.showDeleteDialog -> {
                AlertDialogFragment.newInstance(
                    "삭제?",
                    "아니요",
                    "예",
                    {},
                    { viewModel.deleteFriend(sideEffect.id) },
                ).show(parentFragmentManager, this.tag)
            }
        }
    }

    private fun initAdapter() {
        homeAdapter = HomeAdapter {
            viewModel.showDeleteDialog(it.id)
        }
        homeHeaderAdapter = HomeHeaderAdapter()
        homeHeaderAdapter.submitList(
            mutableListOf(
                Friend(
                    id = null,
                    name = "우상욱",
                    hobby = "안드로이드"
                )
            )
        )
        val combineAdapter = ConcatAdapter(homeHeaderAdapter, homeAdapter)
        binding.rvHome.adapter = combineAdapter
    }

}