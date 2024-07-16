package com.mohaberabi.kmovies.features.details.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class ShowVideoSiteModel(
    val key: String? = null,
    val site: String? = null
)


