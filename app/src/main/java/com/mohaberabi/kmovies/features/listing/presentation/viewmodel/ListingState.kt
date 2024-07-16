package com.mohaberabi.kmovies.features.listing.presentation.viewmodel

import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.util.UiText

data class ListingState(
    val loading: Boolean = false,
    val refreshing: Boolean = false,
    val shows: List<MediaModel> = listOf(),
    val page: Int = 1,
    val endPoint: String = "",
    val title: String = "",
    val error: UiText = UiText.Empty,
    val trending: Boolean = false,
)
