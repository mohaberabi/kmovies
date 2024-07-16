package com.mohaberabi.kmovies.features.search.data.source

import android.database.sqlite.SQLiteException
import com.mohaberabi.kmovies.core.data.source.local.database.dao.ShowSearchDao
import com.mohaberabi.kmovies.core.data.source.local.database.entity.toEntity
import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.util.DispatchersProvider
import com.mohaberabi.kmovies.core.util.error.LocalFailure
import com.mohaberabi.kmovies.core.util.error.mapToErrorModel
import com.mohaberabi.kmovies.core.util.error.toErrorModel
import com.mohaberabi.kmovies.features.search.domain.model.ShowSearchModel
import com.mohaberabi.kmovies.features.search.domain.source.SearchShowsLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomSearchShowLocalDataSource @Inject constructor(
    private val searchDao: ShowSearchDao,
    private val dispatchers: DispatchersProvider,
) : SearchShowsLocalDataSource {
    override suspend fun insertSearchShows(shows: List<ShowSearchModel>) {

        withContext(dispatchers.io) {
            try {
                searchDao.insertSearchShow(shows.map { it.toEntity() })
            } catch (e: SQLiteException) {
                e.printStackTrace()
                throw LocalFailure(e.toErrorModel())
            } catch (e: Exception) {
                e.printStackTrace()
                throw LocalFailure(e.mapToErrorModel())
            }
        }
    }


    override fun searchShows(query: String): Flow<List<Int>> =
        searchDao.searchShows(query).flowOn(dispatchers.io)
}