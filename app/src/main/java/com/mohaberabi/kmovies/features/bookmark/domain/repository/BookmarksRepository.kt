package com.mohaberabi.kmovies.features.bookmark.domain.repository

import com.mohaberabi.kmovies.core.data.source.local.database.entity.BookmarkEntity
import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.util.AppResult
import kotlinx.coroutines.flow.Flow

interface BookmarksRepository {

    suspend fun addBookmark(show: MediaModel): AppResult<Unit>

    suspend fun removeFromBookmark(id: Int): AppResult<Unit>

    fun isBookmarked(id: Int): Flow<Boolean>

    fun getBookmarks(): Flow<List<MediaModel>>


}