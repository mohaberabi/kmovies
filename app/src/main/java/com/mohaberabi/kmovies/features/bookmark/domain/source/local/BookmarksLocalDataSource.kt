package com.mohaberabi.kmovies.features.bookmark.domain.source.local

import com.mohaberabi.kmovies.core.domain.model.MediaModel
import kotlinx.coroutines.flow.Flow

interface BookmarksLocalDataSource {


    suspend fun addBookmark(show: MediaModel)

    suspend fun removeFromBookmark(id: Int)

    fun isBookmarked(id: Int): Flow<Boolean>


    fun getBookmarks(): Flow<List<MediaModel>>
}