package com.mohaberabi.kmovies.core.domain.model

import com.mohaberabi.kmovies.core.data.source.remote.api.MediaApi
import com.mohaberabi.kmovies.features.details.domain.model.ShowVideoSiteModel
import kotlinx.serialization.Serializable

@Serializable
data class MediaModel(
    val mediaId: Int,
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<String>,
    val mediaType: String,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val category: String,
    val similarIds: Set<Int> = setOf(),
    val trailerUrl: ShowVideoSiteModel = ShowVideoSiteModel()
) {

    val backdropFullPath = "${MediaApi.IMAGE_BASE_URL}${backdropPath}"
    val posterFullPath = "${MediaApi.IMAGE_BASE_URL}${posterPath}"

    companion object {
        val empty: MediaModel = MediaModel(
            mediaId = 0,
            adult = false,
            backdropPath = "",
            genreIds = listOf(),
            mediaType = "",
            originCountry = listOf(),
            originalTitle = "",
            originalLanguage = "",
            overview = ",",
            posterPath = "",
            releaseDate = "",
            title = "",
            voteCount = 1,
            voteAverage = 0.2,
            category = "",
            popularity = 1.1
        )
    }

    val isEmpty: Boolean
        get() = this == empty
}