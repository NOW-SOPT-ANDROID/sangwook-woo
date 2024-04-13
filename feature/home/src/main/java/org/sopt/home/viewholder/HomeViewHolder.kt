package org.sopt.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.sopt.home.databinding.ItemHomeFriendBinding
import org.sopt.model.Friend

class HomeViewHolder(
    private val binding: ItemHomeFriendBinding,
    private val onLongClicked: (Friend) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    private var data: Friend? = null
    init {
        binding.root.setOnLongClickListener {
            onLongClicked(data ?: return@setOnLongClickListener false)
            true
        }
    }
    fun onBind(data: Friend?) {
        this.data = data
        binding.run {
            tvHomeFriendName.text = data?.name
            tvHomeFriendHobby.text = data?.hobby
        }
    }
}