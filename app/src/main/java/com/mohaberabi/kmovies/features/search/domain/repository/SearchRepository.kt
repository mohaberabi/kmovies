package com.mohaberabi.kmovies.features.search.domain.repository

import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.util.AppResult
import kotlinx.coroutines.flow.Flow

interface SearchRepository {


    fun searchShows(
        query: String,
        page: Int,
        forceRemote: Boolean
    ): Flow<AppResult<List<MediaModel>>>
}