package org.sopt.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.home.databinding.ItemHomeFriendBinding
import org.sopt.home.databinding.ItemHomeMyinfoBinding
import org.sopt.home.viewholder.HomeHeaderViewHolder
import org.sopt.model.Friend
import org.sopt.ui.view.ItemDiffCallback

class HomeHeaderAdapter(
) : ListAdapter<Friend, HomeHeaderViewHolder>(DiffUtil) {
    override fun onBindViewHolder(holder: HomeHeaderViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHeaderViewHolder {
        return HomeHeaderViewHolder(
            ItemHomeMyinfoBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    companion object {
        private val DiffUtil = ItemDiffCallback<Friend>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}