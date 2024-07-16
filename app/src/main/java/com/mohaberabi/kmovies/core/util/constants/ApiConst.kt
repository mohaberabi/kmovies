package com.mohaberabi.kmovies.core.util.constants

import com.mohaberabi.kmovies.core.domain.model.Genre


object ApiConst {
    const val POPULAR = "popular"
    const val VIDEOS = "videos"
    const val SIMILAR = "videos"

    const val TRENDING = "trending"
    const val TRENDING_TIME = "day"
    const val ALL = "all"
    const val MOVIE = "movie"
    const val TV = "tv"


    val genres = listOf(
        Genre(12, "Adventure"),
        Genre(14, "Fantasy"),
        Genre(16, "Animation"),
        Genre(18, "Drama"),
        Genre(27, "Horror"),
        Genre(28, "Action"),
        Genre(35, "Comedy"),
        Genre(36, "History"),
        Genre(37, "Western"),
        Genre(53, "Thriller"),
        Genre(80, "Crime"),
        Genre(99, "Documentary"),
        Genre(878, "Science Fiction"),
        Genre(9648, "Mystery"),
        Genre(10402, "Music"),
        Genre(10751, "Family"),
        Genre(10752, "War"),
        Genre(10759, "Action & Adventure"),
        Genre(10762, "Kids"),
        Genre(10763, "News"),
        Genre(10764, "Reality"),
        Genre(10765, "Sci-Fi & Fantasy"),
        Genre(10766, "Soap"),
        Genre(10767, "Talk"),
        Genre(10768, "War & Politics"),
        Genre(10770, "TV Movie")
    )
}