package com.mohaberabi.kmovies.features.details.domain.source.local

import com.mohaberabi.kmovies.features.details.domain.model.ShowVideoSiteModel

interface ShowVideoLocalDatasource {
    suspend fun getShowVideo(
        showId: String,
    ): ShowVideoSiteModel?

    suspend fun addShowVideo(
        video: ShowVideoSiteModel,
        showId: String,
    )
}