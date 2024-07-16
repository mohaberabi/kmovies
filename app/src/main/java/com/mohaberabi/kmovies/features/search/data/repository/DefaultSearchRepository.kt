package com.mohaberabi.kmovies.features.search.data.repository

import com.mohaberabi.kmovies.core.di.ApplicationScope
import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.domain.source.local.MediaLocalDataSource
import com.mohaberabi.kmovies.core.domain.source.remote.MediaRemoteDataSource
import com.mohaberabi.kmovies.core.util.AppResult
import com.mohaberabi.kmovies.core.util.DispatchersProvider
import com.mohaberabi.kmovies.core.util.error.AppThrowable
import com.mohaberabi.kmovies.features.search.domain.model.ShowSearchModel
import com.mohaberabi.kmovies.features.search.domain.repository.SearchRepository
import com.mohaberabi.kmovies.features.search.domain.source.SearchShowsLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import javax.inject.Inject

class DefaultSearchRepository @Inject constructor(
    private val searchShowsLocalDataSource: SearchShowsLocalDataSource,
    private val mediaLocalDataSource: MediaLocalDataSource,
    private val mediaRemoteDataSource: MediaRemoteDataSource,
    private val dispatchers: DispatchersProvider,
    @ApplicationScope private val appScope: CoroutineScope
) : SearchRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun searchShows(
        query: String,
        page: Int,
        forceRemote: Boolean
    ): Flow<AppResult<List<MediaModel>>> {
        return flow {

            try {
                if (forceRemote) {
                    val remote = mediaRemoteDataSource.searchShows(
                        query = query, page = page
                    )
                    insertFoundItems(remote)
                    emit(AppResult.Done(remote ?: emptyList<MediaModel>()))
                    return@flow
                } else {
                    val ids = searchShowsLocalDataSource.searchShows(query).first().toSet()
                    val localResult =
                        mediaLocalDataSource.getAllWhereIn(ids)
                    if (localResult.isEmpty()) {
                        val remote = mediaRemoteDataSource.searchShows(
                            query = query, page = page
                        )
                        insertFoundItems(remote)
                        emit(AppResult.Done(remote ?: emptyList()))
                    } else {
                        emit(AppResult.Done(localResult))
                    }
                    return@flow
                }

            } catch (e: AppThrowable) {
                emit(AppResult.Error(e.error))
                return@flow
            }

        }.flowOn(dispatchers.io)
    }

    private suspend fun insertFoundItems(items: List<MediaModel>?) {
        items?.let {
            val mediasDeferred = appScope.launch {
                mediaLocalDataSource.upsertMediaList(it)
            }
            val searchDeferred = appScope.launch {
                searchShowsLocalDataSource.insertSearchShows(it.map { show ->
                    ShowSearchModel(
                        id = show.mediaId,
                        title = show.title
                    )
                })
            }

            joinAll(mediasDeferred, searchDeferred)

        }


    }
}