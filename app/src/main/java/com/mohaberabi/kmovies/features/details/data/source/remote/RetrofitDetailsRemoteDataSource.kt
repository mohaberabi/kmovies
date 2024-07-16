package com.mohaberabi.kmovies.features.details.data.source.remote

import coil.network.HttpException
import com.mohaberabi.kmovies.core.data.source.mapper.toMedia
import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.util.DispatchersProvider
import com.mohaberabi.kmovies.core.util.error.RemoteFailure
import com.mohaberabi.kmovies.core.util.error.mapToErrorModel
import com.mohaberabi.kmovies.core.util.error.toErrorModel
import com.mohaberabi.kmovies.features.details.data.source.remote.dto.toShowVideo
import com.mohaberabi.kmovies.features.details.domain.model.ShowVideoSiteModel
import com.mohaberabi.kmovies.features.details.domain.source.remote.ShowDetailsRemoteDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RetrofitDetailsRemoteDataSource @Inject constructor(
    private val detailsApi: DetailsApi,
    private val dispatchers: DispatchersProvider,
) : ShowDetailsRemoteDataSource {
    override suspend fun getShowSimilar(
        type: String,
        id: Int,
        page: Int,
    ): List<MediaModel> {

        return withContext(dispatchers.io) {
            try {
                val response = detailsApi.getShowSimilar(
                    type = type,
                    id = id,
                    page = page,
                )
                response?.results?.map {
                    it.toMedia(
                        type = type,
                        category = type
                    )
                } ?: emptyList()
            } catch (e: HttpException) {
                e.printStackTrace()
                throw RemoteFailure(e.toErrorModel())
            } catch (e: Exception) {
                e.printStackTrace()
                throw RemoteFailure(e.mapToErrorModel())
            }
        }

    }

    override suspend fun getShowVideos(type: String, id: Int): List<ShowVideoSiteModel> {
        return withContext(dispatchers.io) {
            try {
                val response = detailsApi.getShowVideos(
                    type = type,
                    id = id,
                )
                response?.results?.map {
                    it.toShowVideo()
                } ?: emptyList()
            } catch (e: HttpException) {
                e.printStackTrace()
                throw RemoteFailure(e.toErrorModel())
            } catch (e: Exception) {
                e.printStackTrace()
                throw RemoteFailure(e.mapToErrorModel())
            }
        }
    }

}