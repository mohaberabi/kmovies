package com.mohaberabi.kmovies.features.details.presentation.viewmodel

import com.mohaberabi.kmovies.core.domain.model.MediaModel


data class ShowDetailState(
    val show: MediaModel = MediaModel.empty,
    val loading: Boolean = false,
    val page: Int = 1,
    val similar: List<MediaModel> = listOf(),
    val isBookmarked: Boolean = false,
)