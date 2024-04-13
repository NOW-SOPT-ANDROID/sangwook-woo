package org.sopt.home.add

import android.os.Bundle
import android.view.View
import org.sopt.home.databinding.FragmentAddFriendBottomSheetBinding
import org.sopt.model.Friend
import org.sopt.ui.base.BindingBottomSheetDialogFragment


class AddFriendBottomSheetFragment :
    BindingBottomSheetDialogFragment<FragmentAddFriendBottomSheetBinding>
        ({ FragmentAddFriendBottomSheetBinding.inflate(it) }) {
    private var handleSave: (Friend) -> Unit = {}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivAddFriendBottomSheetClose.setOnClickListener {
            dismiss()
        }
        binding.tvAddFriendAdd.setOnClickListener {
            val friend = Friend(
                id = null,
                binding.etAddFriendName.text.toString(),
                binding.etAddFriendHobby.text.toString(),
            )
            handleSave.invoke(friend)
            dismiss()
        }
    }

    companion object {
        fun newInstance(handleSaveButton: (Friend) -> Unit): AddFriendBottomSheetFragment {
            return AddFriendBottomSheetFragment().apply {
                handleSave = handleSaveButton
            }
        }
    }
}
