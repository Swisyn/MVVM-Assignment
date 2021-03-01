package com.dzhunet.hasan.autoscout24_assignment.ui.feed.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dzhunet.hasan.autoscout24_assignment.R
import com.dzhunet.hasan.autoscout24_assignment.data.network.responses.FeedResultModel
import com.dzhunet.hasan.autoscout24_assignment.databinding.ItemCarPhotoBinding

class FeedItemPhotoSliderViewHolder(
    private val binding: ItemCarPhotoBinding,
    private val feedItem: FeedResultModel? = null,
    private var onItemClick: ((FeedResultModel) -> Unit)? = null
) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: FeedResultModel.Image) {
        binding.imageViewCarPhoto.load(item.url) {
            crossfade(true)
            placeholder(R.drawable.bg_empty_placeholder)
        }

        feedItem?.let {
            binding.imageViewCarPhoto.setOnClickListener { _->
                onItemClick?.invoke(it)
            }
        }
    }
}