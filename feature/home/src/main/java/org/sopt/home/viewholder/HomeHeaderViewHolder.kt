package org.sopt.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.sopt.home.databinding.ItemHomeFriendBinding
import org.sopt.home.databinding.ItemHomeMyinfoBinding
import org.sopt.model.Friend

class HomeHeaderViewHolder(
    private val binding: ItemHomeMyinfoBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: Friend?) {
        binding.run {
            tvHomeFriendName.text = data?.name
            tvHomeFriendHobby.text = data?.hobby
        }
    }
}