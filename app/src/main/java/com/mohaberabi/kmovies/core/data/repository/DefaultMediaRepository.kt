package com.mohaberabi.kmovies.core.data.repository

import com.mohaberabi.kmovies.core.data.source.local.RoomMediaLocalDataSource
import com.mohaberabi.kmovies.core.di.ApplicationScope
import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.domain.repository.EmptyResult
import com.mohaberabi.kmovies.core.domain.repository.MediaRepository
import com.mohaberabi.kmovies.core.domain.source.local.MediaLocalDataSource
import com.mohaberabi.kmovies.core.domain.source.remote.MediaRemoteDataSource
import com.mohaberabi.kmovies.core.util.AppResult
import com.mohaberabi.kmovies.core.util.DispatchersProvider
import com.mohaberabi.kmovies.core.util.constants.ApiConst
import com.mohaberabi.kmovies.core.util.error.AppThrowable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import javax.inject.Inject

class DefaultMediaRepository @Inject constructor(
    private val mediaLocalDataSource: MediaLocalDataSource,
    private val mediaRemoteDataSource: MediaRemoteDataSource,
    @ApplicationScope private val appScope: CoroutineScope,
    private val dispatchers: DispatchersProvider
) : MediaRepository {
    override suspend fun upsertMediaList(
        medias: List<MediaModel>,
    ): EmptyResult {
        return try {
            mediaLocalDataSource.upsertMediaList(medias)
            AppResult.Done(Unit)
        } catch (e: AppThrowable) {
            AppResult.Error(e.error)
        }
    }

    override suspend fun upsertMedia(
        media: MediaModel,
    ): EmptyResult {
        return try {
            mediaLocalDataSource.upsertMediaItem(media)
            AppResult.Done(Unit)
        } catch (e: AppThrowable) {
            AppResult.Error(e.error)
        }
    }

    override suspend fun getMediasByCategory(
        category: String,
    ): AppResult<List<MediaModel>> {
        return try {
            val medias = mediaLocalDataSource.getMediaListByCategory(category)
            AppResult.Done(medias)
        } catch (e: AppThrowable) {
            AppResult.Error(e.error)
        }
    }

    override suspend fun getTrending(
        forceFetchFromRemote: Boolean,
        isRefresh: Boolean,
        type: String,
        time: String,
        page: Int
    ): AppResult<List<MediaModel>> {
        try {
            val local = mediaLocalDataSource.getMediaListByCategory(ApiConst.TRENDING)

            val loadLocal = local.isNotEmpty() && !isRefresh && !forceFetchFromRemote

            if (loadLocal) {
                return AppResult.Done(local)
            } else {
                val remotePage = if (isRefresh) 1 else page
                val remote = mediaRemoteDataSource.getAllTrending(
                    type = type,
                    time = time,
                    page = remotePage,
                )

                val deleteDeferred = appScope.launch(dispatchers.io) {
                    if (isRefresh) {
                        mediaLocalDataSource.deleteAllMediaListByCategory(ApiConst.TRENDING)
                    }
                }

                val upsertDeferred = appScope.launch(dispatchers.io) {
                    remote?.let {
                        mediaLocalDataSource.upsertMediaList(it)
                    }
                }
                joinAll(
                    upsertDeferred,
                    deleteDeferred,
                )
                return AppResult.Done(remote ?: emptyList())

            }

        } catch (e: AppThrowable) {
            return AppResult.Error(e.error)
        }
    }

    override suspend fun getShows(
        forceFetchFromRemote: Boolean,
        isRefresh: Boolean,
        type: String,
        category: String,
        page: Int
    ): AppResult<List<MediaModel>> {

        try {
            val local = mediaLocalDataSource.getMediaListByTypeAndCategory(
                category = category,
                mediaType = type

            )

            val loadLocal = local.isNotEmpty() && !isRefresh && !forceFetchFromRemote

            if (loadLocal) {
                return AppResult.Done(local)
            } else {
                val remotePage = if (isRefresh) 1 else page

                val remote = mediaRemoteDataSource.getShows(
                    category = category,
                    type = type,
                    page = remotePage,
                )


                val deleteDeferred = appScope.launch(dispatchers.io) {
                    if (isRefresh) {
                        mediaLocalDataSource.deleteAllMediaListByTypeAndCategory(
                            category = category,
                            mediaType = type
                        )
                    }
                }

                val upsertDeferred = appScope.launch(dispatchers.io) {
                    remote?.let {
                        mediaLocalDataSource.upsertMediaList(it)
                    }
                }
                joinAll(
                    upsertDeferred,
                    deleteDeferred,
                )

                return AppResult.Done(remote ?: listOf())
            }

        } catch (e: AppThrowable) {
            return AppResult.Error(e.error)

        }
    }


}