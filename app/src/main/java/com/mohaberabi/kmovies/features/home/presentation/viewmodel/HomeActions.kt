package com.mohaberabi.kmovies.features.home.presentation.viewmodel


sealed interface HomeActions {
    data object Refresh : HomeActions
    data object Retry : HomeActions

}