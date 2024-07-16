package com.mohaberabi.kmovies.features.bookmark.data.source.local

import android.database.sqlite.SQLiteException
import com.mohaberabi.kmovies.core.data.source.local.database.dao.BookmarksDao
import com.mohaberabi.kmovies.core.data.source.local.database.entity.toBookmarkEntity
import com.mohaberabi.kmovies.core.data.source.local.database.entity.toShow
import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.util.DispatchersProvider
import com.mohaberabi.kmovies.core.util.error.LocalFailure
import com.mohaberabi.kmovies.core.util.error.mapToErrorModel
import com.mohaberabi.kmovies.core.util.error.toErrorModel
import com.mohaberabi.kmovies.features.bookmark.domain.source.local.BookmarksLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomBookmarksLocalDataSource @Inject constructor(
    private val bookmarksDao: BookmarksDao,
    private val dispatchers: DispatchersProvider,
) : BookmarksLocalDataSource {
    override suspend fun addBookmark(show: MediaModel) {

        withContext(dispatchers.io) {
            try {
                bookmarksDao.addBookmark(show.toBookmarkEntity())
            } catch (e: SQLiteException) {
                e.printStackTrace()
                throw LocalFailure(e.toErrorModel())
            } catch (e: Exception) {
                e.printStackTrace()
                throw LocalFailure(e.mapToErrorModel())
            }
        }
    }

    override fun getBookmarks(): Flow<List<MediaModel>> =
        bookmarksDao.getBookmarks().map { list -> list.map { it.toShow() } }.flowOn(dispatchers.io)

    override suspend fun removeFromBookmark(id: Int) {
        withContext(dispatchers.io) {
            try {
                bookmarksDao.removeFromBookmark(id)
            } catch (e: SQLiteException) {
                e.printStackTrace()
                throw LocalFailure(e.toErrorModel())
            } catch (e: Exception) {
                e.printStackTrace()
                throw LocalFailure(e.mapToErrorModel())
            }
        }
    }

    override fun isBookmarked(id: Int): Flow<Boolean> =
        bookmarksDao.isBookmarked(id).flowOn(dispatchers.io)
}