package com.dzhunet.hasan.autoscout24_assignment.data.network.api

import com.dzhunet.hasan.autoscout24_assignment.data.network.responses.NoteResultModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NotesApi {
    @GET(".")
    suspend fun getNotes(): Response<MutableList<NoteResultModelItem>>
}
