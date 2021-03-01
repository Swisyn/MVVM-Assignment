package com.dzhunet.hasan.autoscout24_assignment.di

import com.dzhunet.hasan.autoscout24_assignment.data.network.api.FeedsApi
import com.dzhunet.hasan.autoscout24_assignment.data.network.api.NotesApi
import com.dzhunet.hasan.autoscout24_assignment.data.repository.FeedListDataSource
import com.dzhunet.hasan.autoscout24_assignment.data.repository.FeedListRepository
import com.dzhunet.hasan.autoscout24_assignment.data.repository.NotesDataSource
import com.dzhunet.hasan.autoscout24_assignment.data.repository.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providesFeedListDataSource(api: FeedsApi): FeedListDataSource {
        return FeedListDataSource(api = api)
    }

    @Provides
    @Singleton
    fun providesFeedListRepository(feedListDataSource: FeedListDataSource): FeedListRepository {
        return FeedListRepository(feedListDataSource = feedListDataSource)
    }

    @Provides
    @Singleton
    fun providesNotesDataSource(api: NotesApi): NotesDataSource {
        return NotesDataSource(api = api)
    }

    @Provides
    @Singleton
    fun providesNotesRepository(notesDataSource: NotesDataSource): NotesRepository {
        return NotesRepository(notesDataSource = notesDataSource)
    }
}