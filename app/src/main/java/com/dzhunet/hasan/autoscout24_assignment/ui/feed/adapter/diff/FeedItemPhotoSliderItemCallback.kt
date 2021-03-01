package com.dzhunet.hasan.autoscout24_assignment.ui.feed.adapter.diff

import androidx.recyclerview.widget.DiffUtil
import com.dzhunet.hasan.autoscout24_assignment.data.network.responses.FeedResultModel


/**
 * Created by [Dzhunet Hasan] on [01 Mar, 2021]. | [KOBIL Systems GmbH] | [dzhunet.hasan@kobil.com]
 */
class FeedItemPhotoSliderItemCallback : DiffUtil.ItemCallback<FeedResultModel.Image>() {
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
}