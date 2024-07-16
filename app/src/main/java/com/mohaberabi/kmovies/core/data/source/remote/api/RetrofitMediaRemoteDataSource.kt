package com.mohaberabi.kmovies.core.data.source.remote.api

import coil.network.HttpException
import com.mohaberabi.kmovies.core.data.source.mapper.toMedia
import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.domain.source.remote.MediaRemoteDataSource
import com.mohaberabi.kmovies.core.util.DispatchersProvider
import com.mohaberabi.kmovies.core.util.constants.ApiConst
import com.mohaberabi.kmovies.core.util.error.CommonAppError
import com.mohaberabi.kmovies.core.util.error.AppThrowable
import com.mohaberabi.kmovies.core.util.error.CommonFailure
import com.mohaberabi.kmovies.core.util.error.ErrorModel
import com.mohaberabi.kmovies.core.util.error.RemoteFailure
import com.mohaberabi.kmovies.core.util.error.mapToErrorModel
import com.mohaberabi.kmovies.core.util.error.toErrorModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitMediaRemoteDataSource @Inject constructor(
    private val mediaApi: MediaApi,
    private val dispatchers: DispatchersProvider
) : MediaRemoteDataSource {
    override suspend fun searchShows(query: String, page: Int): List<MediaModel>? {
        return withContext(dispatchers.io) {
            try {
                val response = mediaApi.searchShows(
                    query = query,
                    page = page,
                )
                response?.results?.map {
                    it.toMedia(
                        type = "all",
                        category = "all"
                    )
                } ?: emptyList()
            } catch (e: HttpException) {
                e.printStackTrace()
                throw RemoteFailure(e.mapToErrorModel())
            } catch (e: Exception) {
                e.printStackTrace()
                throw RemoteFailure(e.mapToErrorModel())
            }
        }
    }

    override suspend fun getAllTrending(
        type: String,
        time: String,
        page: Int,
    ): List<MediaModel>? {
        return withContext(dispatchers.io) {
            try {
                val result = mediaApi.getAllTrending(
                    type = type,
                    time = time,
                    page = page,
                )
                val medias = result?.results?.map {
                    it.toMedia(
                        category = ApiConst.TRENDING,
                        type = type,
                    )
                }
                medias
            } catch (e: HttpException) {
                e.printStackTrace()
                throw RemoteFailure(e.toErrorModel())
            } catch (e: Exception) {
                e.printStackTrace()
                throw RemoteFailure(e.mapToErrorModel())
            }
        }
    }

    override suspend fun getShows(
        category: String,
        type: String,
        page: Int,
    ): List<MediaModel>? {
        return withContext(dispatchers.io) {
            try {
                val result = mediaApi.getShows(
                    type = type,
                    category = category,
                    page = page,
                )
                val medias = result?.results?.map { it.toMedia(category = category, type = type) }
                medias
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