package org.sopt.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.home.databinding.ItemHomeFriendBinding
import org.sopt.model.Friend
import org.sopt.model.ReqresUser

class HomeViewHolder(
    private val binding: ItemHomeFriendBinding,
) : RecyclerView.ViewHolder(binding.root) {
    private var data: ReqresUser? = null
    fun onBind(user: ReqresUser?) {
        this.data = user
        binding.run {
            ivHomeFriend.load(data?.avatar)
            tvHomeFriendName.text = data?.firstName
            tvHomeFriendHobby.text = data?.email
        }
    }
}