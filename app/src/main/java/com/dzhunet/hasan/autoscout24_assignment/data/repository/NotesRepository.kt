package com.dzhunet.hasan.autoscout24_assignment.data.repository

import com.dzhunet.hasan.autoscout24_assignment.data.network.responses.NoteResultModelItem
import com.dzhunet.hasan.autoscout24_assignment.data.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NotesRepository @Inject constructor(private val notesDataSource: NotesDataSource) {

    suspend fun getNotes(): MutableList<NoteResultModelItem> {
        return suspendCoroutine { continuation ->
            GlobalScope.launch(Dispatchers.IO) {
                val responseStatus = Resource.success(notesDataSource.getNotes())

                if (responseStatus.status == Resource.Status.SUCCESS) {
                    continuation.resume(responseStatus.data?.data ?: mutableListOf())
                } else {
                    continuation.resume(mutableListOf())
                }
            }
        }
    }

}