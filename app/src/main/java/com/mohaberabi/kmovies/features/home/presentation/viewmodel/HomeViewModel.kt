package com.mohaberabi.kmovies.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.kmovies.core.domain.repository.MediaRepository
import com.mohaberabi.kmovies.core.util.AppResult
import com.mohaberabi.kmovies.core.util.DispatchersProvider
import com.mohaberabi.kmovies.core.util.UiText
import com.mohaberabi.kmovies.core.util.constants.ApiConst
import com.mohaberabi.kmovies.core.util.error.asUiText
import com.mohaberabi.kmovies.features.home.domain.model.HomeScreenData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mediaRepository: MediaRepository,
    private val dispatchers: DispatchersProvider,
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {

        getData()
    }

    fun onAction(action: HomeActions) {
        when (action) {
            HomeActions.Refresh -> getData(
                forceRemote = true,
                refresh = true
            )

            HomeActions.Retry -> getData(
                forceRemote = false,
                refresh = false,
            )
        }
    }


    private fun getData(
        forceRemote: Boolean = false,
        refresh: Boolean = false,
    ) {
        _state.update { it.copy(loading = true, refreshing = refresh) }
        val trendingDeferred = viewModelScope.async(dispatchers.io) {
            mediaRepository.getTrending(
                forceFetchFromRemote = forceRemote,
                isRefresh = refresh,
                type = ApiConst.ALL,
                time = ApiConst.TRENDING_TIME
            )
        }


        val tvDeferred = viewModelScope.async(dispatchers.io) {
            mediaRepository.getShows(
                forceFetchFromRemote = forceRemote,
                isRefresh = refresh,
                type = ApiConst.TV,
                category = ApiConst.POPULAR,
            )
        }

        val moviesDeferred = viewModelScope.async(dispatchers.io) {
            mediaRepository.getShows(
                forceFetchFromRemote = forceRemote,
                isRefresh = refresh,
                type = ApiConst.MOVIE,
                category = ApiConst.POPULAR,
            )
        }

        viewModelScope.launch {
            val results = awaitAll(
                moviesDeferred,
                tvDeferred,
                trendingDeferred
            )
            if (results.all { it is AppResult.Done }) {
                val values = results.map { (it as AppResult.Done).data }
                val movies = values[0]
                val tv = values[1]
                val trending = values[2]
                val data = HomeScreenData(
                    trending = trending,
                    tvs = tv,
                    movies = movies
                )
                _state.update {
                    it.copy(
                        data = data,
                        error = UiText.Empty,
                    )
                }
            } else {
                val error = results.filterIsInstance<AppResult.Error>()
                    .firstOrNull()?.error?.errorType?.asUiText()
                    ?: UiText.unKnown
                _state.update { it.copy(error = error) }

            }
            _state.update { it.copy(loading = false, refreshing = false) }
        }


    }


}