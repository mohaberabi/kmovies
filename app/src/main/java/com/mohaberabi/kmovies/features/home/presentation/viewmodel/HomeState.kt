package com.mohaberabi.kmovies.features.home.presentation.viewmodel

import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.util.UiText
import com.mohaberabi.kmovies.features.home.domain.model.HomeScreenData


data class HomeState(
    val loading: Boolean = false,
    val refreshing: Boolean = false,
    val data: HomeScreenData = HomeScreenData(),
    val error: UiText = UiText.Empty
)
