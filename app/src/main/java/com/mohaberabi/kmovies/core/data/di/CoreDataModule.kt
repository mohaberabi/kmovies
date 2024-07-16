package com.mohaberabi.kmovies.core.data.di

import android.content.Context
import android.provider.MediaStore.Audio.Media
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mohaberabi.kmovies.core.data.repository.DefaultMediaRepository
import com.mohaberabi.kmovies.core.data.source.local.RoomMediaLocalDataSource
import com.mohaberabi.kmovies.core.data.source.local.database.convertor.MediaTypeCovnertors
import com.mohaberabi.kmovies.core.data.source.local.database.db.MediaDatabase
import com.mohaberabi.kmovies.core.data.source.remote.api.MediaApi
import com.mohaberabi.kmovies.core.data.source.remote.api.RetrofitMediaRemoteDataSource
import com.mohaberabi.kmovies.core.di.ApplicationScope
import com.mohaberabi.kmovies.core.domain.repository.MediaRepository
import com.mohaberabi.kmovies.core.domain.source.local.MediaLocalDataSource
import com.mohaberabi.kmovies.core.domain.source.remote.MediaRemoteDataSource
import com.mohaberabi.kmovies.core.util.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CoreDataModule {


    @Provides
    @Singleton
    fun provideApi(
        convertor: Converter.Factory,
        client: OkHttpClient,
    ): MediaApi = Retrofit.Builder()
        .baseUrl(MediaApi.BASE_URL).client(client)
        .addConverterFactory(convertor)
        .build()
        .create(MediaApi::class.java)


    @Singleton
    @Provides
    fun provideMediaLocalDatasource(
        mediaDatabase: MediaDatabase,
        dispatchers: DispatchersProvider,
    ): MediaLocalDataSource = RoomMediaLocalDataSource(
        mediaDao = mediaDatabase.mediaDao(),
        dispatchers = dispatchers,
    )


    @Singleton

    @Provides

    fun provideMediaRepository(
        mediaLocalDataSource: MediaLocalDataSource,
        mediaRemoteDataSource: MediaRemoteDataSource,
        dispatchers: DispatchersProvider,
        @ApplicationScope applicationScope: CoroutineScope
    ): MediaRepository = DefaultMediaRepository(
        appScope = applicationScope,
        mediaLocalDataSource = mediaLocalDataSource,
        mediaRemoteDataSource = mediaRemoteDataSource,
        dispatchers = dispatchers,
    )


    @Singleton
    @Provides
    fun provideMediaRemoteDataSource(
        dispatchers: DispatchersProvider,
        mediaApi: MediaApi,
    ): MediaRemoteDataSource = RetrofitMediaRemoteDataSource(
        mediaApi = mediaApi,
        dispatchers = dispatchers,
    )
}