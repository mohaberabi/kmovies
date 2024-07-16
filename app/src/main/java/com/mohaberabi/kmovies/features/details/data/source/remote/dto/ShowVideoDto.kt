package com.mohaberabi.kmovies.features.details.data.source.remote.dto

import com.mohaberabi.kmovies.features.details.domain.model.ShowVideoSiteModel
import kotlinx.serialization.Serializable


@Serializable
data class ShowVideoSiteDto(
    val key: String? = null,
    val site: String? = null
)


@Serializable
data class ShowVideosDto(
    val results: List<ShowVideoSiteDto>? = null
)


fun ShowVideoSiteDto.toShowVideo() = ShowVideoSiteModel(
    key = key,
    site = site,
)