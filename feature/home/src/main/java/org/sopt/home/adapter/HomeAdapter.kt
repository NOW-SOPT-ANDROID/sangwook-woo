package org.sopt.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import org.sopt.home.databinding.ItemHomeFriendBinding
import org.sopt.home.viewholder.HomeViewHolder
import org.sopt.model.ReqresUser
import org.sopt.ui.view.ItemDiffCallback

class HomeAdapter(
) : PagingDataAdapter<ReqresUser, HomeViewHolder>(DiffUtil) {
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemHomeFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    companion object {
        private val DiffUtil = ItemDiffCallback<ReqresUser>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}