package com.dzhunet.hasan.autoscout24_assignment.di

import androidx.viewbinding.BuildConfig
import com.dzhunet.hasan.autoscout24_assignment.data.network.api.FeedsApi
import com.dzhunet.hasan.autoscout24_assignment.data.network.api.NotesApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkingModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun providesFeedsApi(
        gson: Gson,
        client: OkHttpClient
    ): FeedsApi {
        return Retrofit.Builder()
            .baseUrl("http://private-fe87c-simpleclassifieds.apiary-mock.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(FeedsApi::class.java)
    }

    @Provides
    @Singleton
    fun providesNotesApi(
        gson: Gson,
        client: OkHttpClient
    ): NotesApi {
        return Retrofit.Builder()
            .baseUrl("https://private-e7c3d8-classifiednotes.apiary-mock.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(NotesApi::class.java)
    }

    @Provides
    fun providesOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
            })
            .build()
    }
}