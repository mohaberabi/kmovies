package com.mohaberabi.kmovies.core.domain.repository

import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.util.AppResult
import kotlinx.coroutines.flow.Flow


typealias EmptyResult = AppResult<Unit>

interface MediaRepository {


    suspend fun upsertMediaList(
        medias: List<MediaModel>
    ): EmptyResult

    suspend fun upsertMedia(
        media: MediaModel
    ): EmptyResult

    suspend fun getMediasByCategory(
        category: String,
    ): AppResult<List<MediaModel>>

    suspend fun getTrending(
        forceFetchFromRemote: Boolean,
        isRefresh: Boolean,
        type: String,
        time: String,
        page: Int = 1
    ): AppResult<List<MediaModel>>

    suspend fun getShows(
        forceFetchFromRemote: Boolean,
        isRefresh: Boolean,
        type: String,
        category: String,
        page: Int = 1
    ): AppResult<List<MediaModel>>

}