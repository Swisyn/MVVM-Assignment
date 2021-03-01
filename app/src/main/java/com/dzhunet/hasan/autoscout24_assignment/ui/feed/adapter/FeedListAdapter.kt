package com.dzhunet.hasan.autoscout24_assignment.ui.feed.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dzhunet.hasan.autoscout24_assignment.data.network.responses.FeedResultModel
import com.dzhunet.hasan.autoscout24_assignment.databinding.ItemFeedBinding
import com.dzhunet.hasan.autoscout24_assignment.ui.feed.adapter.diff.FeedListDiffItemCallback
import com.dzhunet.hasan.autoscout24_assignment.ui.feed.adapter.holder.FeedViewHolder
import com.dzhunet.hasan.autoscout24_assignment.utils.layoutInflater

class FeedListAdapter(private val onItemClick: (FeedResultModel) -> Unit) :
    ListAdapter<FeedResultModel, FeedViewHolder>(FeedListDiffItemCallback()) {
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