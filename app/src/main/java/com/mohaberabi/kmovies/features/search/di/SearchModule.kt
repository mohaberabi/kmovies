package com.mohaberabi.kmovies.features.search.di

import com.mohaberabi.kmovies.core.data.source.local.database.db.MediaDatabase
import com.mohaberabi.kmovies.core.di.ApplicationScope
import com.mohaberabi.kmovies.core.domain.source.local.MediaLocalDataSource
import com.mohaberabi.kmovies.core.domain.source.remote.MediaRemoteDataSource
import com.mohaberabi.kmovies.core.util.DispatchersProvider
import com.mohaberabi.kmovies.features.search.data.repository.DefaultSearchRepository
import com.mohaberabi.kmovies.features.search.data.source.RoomSearchShowLocalDataSource
import com.mohaberabi.kmovies.features.search.domain.repository.SearchRepository
import com.mohaberabi.kmovies.features.search.domain.source.SearchShowsLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)

@Module
object SearchModule {


    @Singleton
    @Provides
    fun provideSearchLocalDataSource(
        appDatabase: MediaDatabase,
        dispatchers: DispatchersProvider,
    ): SearchShowsLocalDataSource = RoomSearchShowLocalDataSource(
        searchDao = appDatabase.searchDao(),
        dispatchers = dispatchers,
    )

    @Singleton
    @Provides
    fun provideSearchRepository(
        searchShowsLocalDataSource: SearchShowsLocalDataSource,
        @ApplicationScope scope: CoroutineScope,
        dispatchers: DispatchersProvider,
        mediaLocalDataSource: MediaLocalDataSource,
        mediaRemoteDataSource: MediaRemoteDataSource,
    ): SearchRepository = DefaultSearchRepository(
        searchShowsLocalDataSource = searchShowsLocalDataSource,
        appScope = scope,
        dispatchers = dispatchers,
        mediaLocalDataSource = mediaLocalDataSource,
        mediaRemoteDataSource = mediaRemoteDataSource
    )
}