package com.dzhunet.hasan.autoscout24_assignment.data.repository

import com.dzhunet.hasan.autoscout24_assignment.data.network.api.NotesApi
import com.dzhunet.hasan.autoscout24_assignment.data.network.data.BaseDataSource
import javax.inject.Inject

class NotesDataSource @Inject constructor(private val api: NotesApi) : BaseDataSource() {
    suspend fun getNotes() = getResult { api.getNotes() }
}