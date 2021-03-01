package com.dzhunet.hasan.autoscout24_assignment.ui.feed.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.dzhunet.hasan.autoscout24_assignment.data.network.responses.FeedResultModel
import com.dzhunet.hasan.autoscout24_assignment.databinding.ItemFeedBinding
import com.dzhunet.hasan.autoscout24_assignment.ui.feed.adapter.holder.FeedViewHolder
import com.dzhunet.hasan.autoscout24_assignment.utils.layoutInflater

class FeedListAdapter( private val onItemClick: (FeedResultModel) -> Unit) :
    ListAdapter<FeedResultModel, FeedViewHolder>(object : DiffUtil.ItemCallback<FeedResultModel>() {
        override fun areItemsTheSame(oldItem: FeedResultModel, newItem: FeedResultModel): Boolean {
           return oldItem.id == newItem.id &&
                   oldItem.colour == newItem.colour &&
                   oldItem.description == newItem.description // TODO add all later
        }

        override fun areContentsTheSame(
            oldItem: FeedResultModel,
            newItem: FeedResultModel
        ): Boolean {
          return oldItem == newItem
        }

    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(
            context = parent.context,
            onItemClick = onItemClick,
            binding = ItemFeedBinding.inflate(
                parent.layoutInflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}