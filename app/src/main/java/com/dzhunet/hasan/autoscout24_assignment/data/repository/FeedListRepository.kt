package com.dzhunet.hasan.autoscout24_assignment.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dzhunet.hasan.autoscout24_assignment.data.network.responses.FeedResultModel
import com.dzhunet.hasan.autoscout24_assignment.data.vo.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class FeedListRepository @Inject constructor(private val feedListDataSource: FeedListDataSource) {

    fun getFeeds(): LiveData<Resource<MutableList<FeedResultModel>>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val responseStatus = Resource.success(feedListDataSource.getFeeds())

            if (responseStatus.status == Resource.Status.SUCCESS) {
                emit(responseStatus.data!!)
            } else if (responseStatus.status == Resource.Status.ERROR) {
                emit(Resource.error(responseStatus.message!!))
            }
        }
}