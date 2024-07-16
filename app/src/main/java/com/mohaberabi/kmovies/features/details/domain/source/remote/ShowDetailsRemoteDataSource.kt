package com.mohaberabi.kmovies.features.details.domain.source.remote

import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.features.details.data.source.remote.dto.ShowVideoSiteDto
import com.mohaberabi.kmovies.features.details.domain.model.ShowVideoSiteModel
import retrofit2.http.Path

interface ShowDetailsRemoteDataSource {


    suspend fun getShowSimilar(
        type: String,
        id: Int,
        page: Int = 1,
    ): List<MediaModel>


    suspend fun getShowVideos(
        type: String,
        id: Int,
    ): List<ShowVideoSiteModel>
}