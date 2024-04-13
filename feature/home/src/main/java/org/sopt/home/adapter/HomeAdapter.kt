package org.sopt.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.home.databinding.ItemHomeFriendBinding
import org.sopt.home.viewholder.HomeViewHolder
import org.sopt.model.Friend
import org.sopt.ui.view.ItemDiffCallback

class HomeAdapter(
    private val onLongClicked: (Friend) -> Unit,
) : ListAdapter<Friend, HomeViewHolder>(DiffUtil) {
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemHomeFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onLongClicked,
        )
    }

    companion object {
        private val DiffUtil = ItemDiffCallback<Friend>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}