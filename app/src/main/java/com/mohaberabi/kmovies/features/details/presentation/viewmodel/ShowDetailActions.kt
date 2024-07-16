package com.mohaberabi.kmovies.features.details.presentation.viewmodel

import com.mohaberabi.kmovies.core.domain.model.MediaModel

sealed interface ShowDetailActions {


    data object PaginateSimilar : ShowDetailActions


    data object AddBookmark : ShowDetailActions


    data object RemoveBookmark : ShowDetailActions
}