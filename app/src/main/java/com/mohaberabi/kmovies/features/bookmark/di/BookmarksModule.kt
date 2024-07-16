package com.mohaberabi.kmovies.features.bookmark.di

import com.mohaberabi.kmovies.core.data.source.local.database.db.MediaDatabase
import com.mohaberabi.kmovies.core.domain.source.remote.MediaRemoteDataSource
import com.mohaberabi.kmovies.core.util.DispatchersProvider
import com.mohaberabi.kmovies.features.bookmark.data.repository.DefaultBookmarksRepository
import com.mohaberabi.kmovies.features.bookmark.data.source.local.RoomBookmarksLocalDataSource
import com.mohaberabi.kmovies.features.bookmark.domain.repository.BookmarksRepository
import com.mohaberabi.kmovies.features.bookmark.domain.source.local.BookmarksLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)


object BookmarksModule {

    @Singleton
    @Provides
    fun provideBookmarksLocalDataSource(
        database: MediaDatabase,
        dispatchers: DispatchersProvider,
    ): BookmarksLocalDataSource = RoomBookmarksLocalDataSource(
        bookmarksDao = database.bookmarksDao(),
        dispatchers = dispatchers,
    )

    @Singleton
    @Provides
    fun provideBookmarksRepository(
        bookmarksLocalDataSource: BookmarksLocalDataSource,
    ): BookmarksRepository = DefaultBookmarksRepository(
        bookmarksLocalDataSource = bookmarksLocalDataSource,
    )


}