package com.mohaberabi.kmovies.core.data.source.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MediaListDto(
    val page: Int? = null,
    val results: List<MediaDto>? = null
)