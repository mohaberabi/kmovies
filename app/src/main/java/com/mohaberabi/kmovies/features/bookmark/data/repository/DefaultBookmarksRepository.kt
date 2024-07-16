package com.mohaberabi.kmovies.features.bookmark.data.repository

import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.util.AppResult
import com.mohaberabi.kmovies.core.util.error.AppThrowable
import com.mohaberabi.kmovies.features.bookmark.data.source.local.RoomBookmarksLocalDataSource
import com.mohaberabi.kmovies.features.bookmark.domain.repository.BookmarksRepository
import com.mohaberabi.kmovies.features.bookmark.domain.source.local.BookmarksLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultBookmarksRepository @Inject constructor(
    private val bookmarksLocalDataSource: BookmarksLocalDataSource,
) : BookmarksRepository {
    override suspend fun addBookmark(show: MediaModel): AppResult<Unit> {
        return try {
            bookmarksLocalDataSource.addBookmark(show)
            AppResult.Done(Unit)
        } catch (e: AppThrowable) {
            e.printStackTrace()
            AppResult.Error(e.error)
        }
    }

    override suspend fun removeFromBookmark(id: Int): AppResult<Unit> {
        return try {
            bookmarksLocalDataSource.removeFromBookmark(id)
            AppResult.Done(Unit)
        } catch (e: AppThrowable) {
            e.printStackTrace()
            AppResult.Error(e.error)
        }
    }

    override fun getBookmarks(): Flow<List<MediaModel>> = bookmarksLocalDataSource.getBookmarks()
    override fun isBookmarked(id: Int): Flow<Boolean> = bookmarksLocalDataSource.isBookmarked(id)
}