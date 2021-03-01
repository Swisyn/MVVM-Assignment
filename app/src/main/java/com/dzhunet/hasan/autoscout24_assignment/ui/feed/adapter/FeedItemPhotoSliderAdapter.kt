package com.dzhunet.hasan.autoscout24_assignment.ui.feed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.dzhunet.hasan.autoscout24_assignment.data.network.responses.FeedResultModel
import com.dzhunet.hasan.autoscout24_assignment.databinding.ItemCarPhotoBinding
import com.dzhunet.hasan.autoscout24_assignment.ui.feed.adapter.holder.FeedItemPhotoSliderViewHolder
import com.dzhunet.hasan.autoscout24_assignment.utils.layoutInflater

class FeedItemPhotoSliderAdapter :
    ListAdapter<FeedResultModel.Image, FeedItemPhotoSliderViewHolder>(object :
        DiffUtil.ItemCallback<FeedResultModel.Image>() {
        override fun areItemsTheSame(
            oldItem: FeedResultModel.Image,
            newItem: FeedResultModel.Image
        ): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(
            oldItem: FeedResultModel.Image,
            newItem: FeedResultModel.Image
        ): Boolean {
            return oldItem == newItem
        }
    }) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeedItemPhotoSliderViewHolder {
        return FeedItemPhotoSliderViewHolder(
            binding = ItemCarPhotoBinding.inflate(
                parent.layoutInflater, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FeedItemPhotoSliderViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}