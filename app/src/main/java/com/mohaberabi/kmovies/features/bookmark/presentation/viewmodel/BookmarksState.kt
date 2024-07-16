package com.mohaberabi.kmovies.features.bookmark.presentation.viewmodel

import com.mohaberabi.kmovies.core.domain.model.MediaModel

sealed interface BookmarksState {


    data object Loading : BookmarksState


    data object Error : BookmarksState
    data class Done(val bookmarks: List<MediaModel>) : BookmarksState
}