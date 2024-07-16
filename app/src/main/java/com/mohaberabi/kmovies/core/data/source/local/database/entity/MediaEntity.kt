package com.mohaberabi.kmovies.core.data.source.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "medias")
data class MediaEntity(
    @PrimaryKey val mediaId: Int,
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<String>,
    var mediaType: String,
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
    var category: String,
    val similarIds: Set<String> = setOf()
)
