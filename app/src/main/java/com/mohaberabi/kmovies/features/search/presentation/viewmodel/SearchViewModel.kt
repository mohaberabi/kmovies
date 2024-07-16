package com.mohaberabi.kmovies.features.search.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.kmovies.core.util.AppResult
import com.mohaberabi.kmovies.features.search.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {


    companion object {
        private const val SEARCH_KEY = "searchKey"
    }

    val searchQuery = savedStateHandle.getStateFlow(SEARCH_KEY, "")

    @OptIn(ExperimentalCoroutinesApi::class)
    val shows = searchQuery.flatMapLatest { query ->
        searchRepository.searchShows(
            query = query,
            forceRemote = false,
            page = 1
        ).map {
            when (it) {
                is AppResult.Done -> it.data
                is AppResult.Error -> emptyList()
            }
        }.map { it }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        emptyList()
    )

    fun onAction(action: SearchActions) {
        when (action) {
            is SearchActions.SearchChanged -> savedStateHandle[SEARCH_KEY] = action.query
        }
    }
}
