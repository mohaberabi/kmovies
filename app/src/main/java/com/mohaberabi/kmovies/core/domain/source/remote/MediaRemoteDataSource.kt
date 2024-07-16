package com.mohaberabi.kmovies.core.domain.source.remote

import com.mohaberabi.kmovies.core.data.source.remote.api.MediaApi.Companion.API_KEY
import com.mohaberabi.kmovies.core.domain.model.MediaModel


interface MediaRemoteDataSource {
    suspend fun getAllTrending(
        type: String,
        time: String,
        page: Int,
    ): List<MediaModel>?

    suspend fun searchShows(
        query: String,
        page: Int,
    ): List<MediaModel>?

    suspend fun getShows(
        category: String,
        type: String,
        page: Int,
    ): List<MediaModel>?
}