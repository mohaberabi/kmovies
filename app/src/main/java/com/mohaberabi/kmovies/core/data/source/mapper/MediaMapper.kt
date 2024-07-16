package com.mohaberabi.kmovies.core.data.source.mapper

import androidx.room.ProvidedTypeConverter
import com.mohaberabi.kmovies.core.data.source.local.database.entity.MediaEntity
import com.mohaberabi.kmovies.core.data.source.remote.dto.MediaDto
import com.mohaberabi.kmovies.core.domain.model.MediaModel


fun MediaModel.toMediaEntity(): MediaEntity {
    return MediaEntity(
        mediaId = mediaId,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        popularity = popularity,
        voteCount = voteCount,
        genreIds = genreIds,
        adult = adult,
        mediaType = mediaType,
        originCountry = originCountry,
        originalTitle = originalTitle,
        category = category,
        similarIds = similarIds.map { it.toString() }.toSet()

    )
}

fun MediaEntity.toMedia(): MediaModel {
    return MediaModel(
        mediaId = mediaId,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        popularity = popularity,
        voteCount = voteCount,
        genreIds = genreIds,
        adult = adult,
        mediaType = mediaType,
        originCountry = originCountry,
        originalTitle = originalTitle,
        category = category,
        similarIds = similarIds.map { it.toIntOrNull() ?: 0 }.toSet()

    )
}

fun MediaDto.toMedia(
    category: String,
    type: String
): MediaModel {
    return MediaModel(
        mediaId = id ?: 0,
        backdropPath = backDropPath ?: "",
        originalLanguage = originalLanguage ?: "",
        overview = overview ?: "",
        posterPath = posterPath ?: "",
        releaseDate = releaseDate ?: firstAirDate ?: "",
        title = title ?: name ?: "",
        originalTitle = originalTitle ?: originalName ?: "",
        voteAverage = voteAverage ?: 0.0,
        popularity = popularity ?: 0.0,
        voteCount = voteCount ?: 0,
        genreIds = genreIds?.let { it.map { id -> id.toString() } } ?: emptyList<String>(),
        adult = adult ?: false,
        mediaType = type,
        category = category,
        originCountry = originCountry ?: emptyList(),
    )
}

fun MediaDto.toMediaEntity(
    type: String,
    category: String
): MediaEntity {
    return MediaEntity(
        mediaId = id ?: 0,
        backdropPath = backDropPath ?: "",
        originalLanguage = originalLanguage ?: "",
        overview = overview ?: "",
        posterPath = posterPath ?: "",
        releaseDate = releaseDate ?: firstAirDate ?: "",
        title = title ?: name ?: "",
        originalTitle = originalTitle ?: originalName ?: "",
        voteAverage = voteAverage ?: 0.0,
        popularity = popularity ?: 0.0,
        voteCount = voteCount ?: 0,
        genreIds = genreIds?.let { it.map { id -> id.toString() } } ?: emptyList<String>(),
        adult = adult ?: false,
        mediaType = type,
        category = category,
        originCountry = originCountry ?: emptyList(),

        )
}