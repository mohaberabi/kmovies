package com.mohaberabi.kmovies.features.search.domain.source

import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.features.search.domain.model.ShowSearchModel
import kotlinx.coroutines.flow.Flow

interface SearchShowsLocalDataSource {


    suspend fun insertSearchShows(shows: List<ShowSearchModel>)
    fun searchShows(query: String): Flow<List<Int>>
}