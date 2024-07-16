package com.mohaberabi.kmovies.features.details.data.source.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ShowDetailsDto(
    val runTime: Int? = null,
    val tagLine: String? = null
)
