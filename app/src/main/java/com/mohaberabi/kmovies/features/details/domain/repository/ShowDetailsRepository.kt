package com.mohaberabi.kmovies.features.details.domain.repository

import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.util.AppResult
import com.mohaberabi.kmovies.features.details.domain.model.ShowVideoSiteModel


interface ShowDetailsRepository {
    suspend fun getSimilarShows(
        media: MediaModel,
        page: Int = 1,
        forceRemoteLoad: Boolean,
    ): AppResult<List<MediaModel>>

    suspend fun getShowVideos(
        type: String,
        id: Int,
    ): AppResult<ShowVideoSiteModel?>
}