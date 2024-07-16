package com.mohaberabi.kmovies.features.listing.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.mohaberabi.kmovies.core.domain.repository.MediaRepository
import com.mohaberabi.kmovies.core.util.AppResult
import com.mohaberabi.kmovies.core.util.constants.ApiConst
import com.mohaberabi.kmovies.core.util.error.asUiText
import com.mohaberabi.kmovies.features.listing.presentation.navigation.ListingRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListingViewModel @Inject constructor(
    private val mediaRepository: MediaRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val routeArgs = ListingRoute.from(savedStateHandle)

    private val _state = MutableStateFlow(
        ListingState(
            title = routeArgs.args.title,
            endPoint = routeArgs.args.endpoint,
            trending = routeArgs.args.trending
        )
    )

    init {
        getData()
    }

    val state = _state.asStateFlow()
    fun onAction(action: ListingActions) {
        when (action) {
            ListingActions.Paginate -> getData(paging = true)
            ListingActions.Refresh -> getData(refreshing = true)
        }
    }


    private fun getData(
        refreshing: Boolean = false,
        paging: Boolean = false,
    ) {

        val stateVal = _state.value

        _state.update { it.copy(loading = !paging) }



        viewModelScope.launch {

            val res =
                if (stateVal.trending) {
                    mediaRepository.getTrending(
                        forceFetchFromRemote = paging,
                        isRefresh = refreshing,
                        type = ApiConst.ALL,
                        page = stateVal.page,
                        time = ApiConst.TRENDING_TIME
                    )
                } else {
                    mediaRepository.getShows(
                        forceFetchFromRemote = paging,
                        isRefresh = refreshing,
                        type = stateVal.endPoint,
                        page = stateVal.page,
                        category = ApiConst.POPULAR,
                    )
                }
            when (res) {
                is AppResult.Done -> {
                    _state.update {
                        it.copy(
                            shows = if (paging) it.shows + res.data else res.data,
                            page = if (paging) it.page + 1 else 1
                        )
                    }
                }

                is AppResult.Error -> {
                    _state.update {
                        it.copy(
                            error = res.error.errorType.asUiText(),
                        )
                    }

                }
            }
            _state.update {
                it.copy(
                    loading = false,
                    refreshing = false
                )
            }

        }


    }
}