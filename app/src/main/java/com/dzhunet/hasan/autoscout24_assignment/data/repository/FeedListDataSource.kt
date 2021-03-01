package com.dzhunet.hasan.autoscout24_assignment.data.repository

import com.dzhunet.hasan.autoscout24_assignment.data.network.api.FeedsApi
import com.dzhunet.hasan.autoscout24_assignment.data.network.data.BaseDataSource
import com.dzhunet.hasan.autoscout24_assignment.data.network.responses.FeedResultModel
import javax.inject.Inject

class FeedListDataSource @Inject constructor(private val api: FeedsApi) : BaseDataSource() {
    suspend fun getFeeds() = getResult { api.getItemsFeed() }
}