package com.dzhunet.hasan.autoscout24_assignment.ui.feed.adapter.diff

import androidx.recyclerview.widget.DiffUtil
import com.dzhunet.hasan.autoscout24_assignment.data.network.responses.FeedResultModel


/**
 * Created by [Dzhunet Hasan] on [01 Mar, 2021]. | [KOBIL Systems GmbH] | [dzhunet.hasan@kobil.com]
 */
class FeedListDiffItemCallback: DiffUtil.ItemCallback<FeedResultModel>() {
    override fun areItemsTheSame(oldItem: FeedResultModel, newItem: FeedResultModel): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.colour == newItem.colour &&
                oldItem.description == newItem.description &&
                oldItem.firstRegistration == newItem.firstRegistration &&
                oldItem.fuel == newItem.fuel &&
                oldItem.images?.size == newItem.images?.size &&
                oldItem.make == newItem.make &&
                oldItem.mileage == newItem.mileage &&
                oldItem.model == newItem.model &&
                oldItem.modelline == newItem.modelline &&
                oldItem.price == newItem.price
    }

    override fun areContentsTheSame(
        oldItem: FeedResultModel,
        newItem: FeedResultModel
    ): Boolean {
        return oldItem == newItem
    }
}