package com.mohaberabi.kmovies.features.details.data.repository

import com.mohaberabi.kmovies.core.di.ApplicationScope
import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.domain.source.local.MediaLocalDataSource
import com.mohaberabi.kmovies.core.util.AppResult
import com.mohaberabi.kmovies.core.util.error.AppThrowable
import com.mohaberabi.kmovies.features.details.domain.model.ShowVideoSiteModel
import com.mohaberabi.kmovies.features.details.domain.repository.ShowDetailsRepository
import com.mohaberabi.kmovies.features.details.domain.source.local.ShowVideoLocalDatasource
import com.mohaberabi.kmovies.features.details.domain.source.remote.ShowDetailsRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import javax.inject.Inject

class DefaultDetailsRepository @Inject constructor(
    private val showsLocalDataSource: MediaLocalDataSource,
    private val showDetailRemoteDatasource: ShowDetailsRemoteDataSource,
    @ApplicationScope private val appScope: CoroutineScope,
    private val showVideoLocalDataSource: ShowVideoLocalDatasource,
) : ShowDetailsRepository {
    companion object {
        private const val DEFAULT_VIDEO_SITE = "Youtube"
    }

    override suspend fun getSimilarShows(
        media: MediaModel,
        page: Int,
        forceRemoteLoad: Boolean,
    ): AppResult<List<MediaModel>> {
        try {
            val local =
                if (media.similarIds.isEmpty()) emptyList() else {
                    showsLocalDataSource.getAllWhereIn(media.similarIds)
                }
            val loadLocal = local.isNotEmpty() && !forceRemoteLoad
            return if (loadLocal) {
                AppResult.Done(local)
            } else {
                val remote = showDetailRemoteDatasource.getShowSimilar(
                    type = media.mediaType, page = page, id = media.mediaId,
                )
                val similarIds = remote.map { it.mediaId }.toSet()
                val upsertAllDeferred =
                    appScope.launch { showsLocalDataSource.upsertMediaList(remote) }
                val upsertOneDeferred =
                    appScope.launch { showsLocalDataSource.upsertMediaItem(media.copy(similarIds = similarIds)) }
                joinAll(
                    upsertAllDeferred,
                    upsertOneDeferred,
                )
                AppResult.Done(remote)
            }
        } catch (e: AppThrowable) {
            e.printStackTrace()
            return AppResult.Error(e.error)
        }
    }

    override suspend fun getShowVideos(
        type: String,
        id: Int,
    ): AppResult<ShowVideoSiteModel?> {
        try {
            val local = showVideoLocalDataSource.getShowVideo(id.toString())
            return if (local == null) {
                val remote = showDetailRemoteDatasource.getShowVideos(
                    type = type,
                    id = id,
                )

                val site = remote.firstOrNull { it.site == DEFAULT_VIDEO_SITE }
                if (site != null) {
                    appScope.launch {
                        showVideoLocalDataSource.addShowVideo(
                            video = site,
                            showId = id.toString()
                        )
                    }.join()
                    AppResult.Done(site)
                } else {
                    AppResult.Done(null)
                }

            } else {
                AppResult.Done(local)
            }
        } catch (e: AppThrowable) {
            e.printStackTrace()
            return AppResult.Error(e.error)
        }

    }
}