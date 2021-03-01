package com.dzhunet.hasan.autoscout24_assignment.data.network.api

import com.dzhunet.hasan.autoscout24_assignment.data.network.responses.FeedResultModel
import retrofit2.Response
import retrofit2.http.GET


interface FeedsApi {
    @GET(".")
    suspend fun getItemsFeed(): Response<MutableList<FeedResultModel>>
}
