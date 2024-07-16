package com.mohaberabi.kmovies.features.search.presentation.viewmodel

sealed interface SearchActions {


    data class SearchChanged(val query: String) : SearchActions
}