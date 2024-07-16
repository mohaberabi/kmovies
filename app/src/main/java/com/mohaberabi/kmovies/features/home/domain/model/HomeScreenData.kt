package com.mohaberabi.kmovies.features.home.domain.model

import com.mohaberabi.kmovies.core.domain.model.MediaModel

data class HomeScreenData(
    val movies: List<MediaModel> = emptyList(),
    val tvs: List<MediaModel> = emptyList(),
    val trending: List<MediaModel> = emptyList(),
) {
    val special: List<MediaModel>
        get() = movies.take(2) + tvs.take(2) + trending.take(2)
}
