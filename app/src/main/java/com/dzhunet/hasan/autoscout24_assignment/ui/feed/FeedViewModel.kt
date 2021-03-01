package com.dzhunet.hasan.autoscout24_assignment.ui.feed

import androidx.lifecycle.ViewModel
import com.dzhunet.hasan.autoscout24_assignment.data.network.responses.NoteResultModelItem
import com.dzhunet.hasan.autoscout24_assignment.data.repository.FeedListRepository
import com.dzhunet.hasan.autoscout24_assignment.data.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    feedListRepository: FeedListRepository,
    private val notesRepository: NotesRepository
) : ViewModel() {

    private var vehicleNotes: MutableList<NoteResultModelItem>? = null

    val getFeeds = feedListRepository.getFeeds()

    init {
        GlobalScope.launch(Dispatchers.IO) {
            vehicleNotes = notesRepository.getNotes()
        }
    }

    fun getVehicleNotes(vehicleId: Int): Array<String>? {
        return vehicleNotes?.filter { it.vehicleId == vehicleId }?.map { item -> "✔ ${item.note}" }
            ?.toTypedArray()
    }
}