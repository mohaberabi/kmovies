package com.mohaberabi.kmovies.features.details.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.util.AppResult
import com.mohaberabi.kmovies.core.util.error.asUiText
import com.mohaberabi.kmovies.features.bookmark.domain.repository.BookmarksRepository
import com.mohaberabi.kmovies.features.details.domain.repository.ShowDetailsRepository
import com.mohaberabi.kmovies.features.details.presentation.navigation.ShowDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class ShowDetailVideModel @Inject constructor(
    private val showDetailsRepository: ShowDetailsRepository,
    private val bookmarksRepository: BookmarksRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val showRoute = ShowDetailRoute.from(savedStateHandle)
    private val selectedShow = Json.decodeFromString<MediaModel>(showRoute.showJson)
    private val _state =
        MutableStateFlow(ShowDetailState(show = selectedShow))

    val state = _state.asStateFlow()


    private val _event = Channel<ShowDetailEvent>()

    val event = _event.receiveAsFlow()

    init {
        getSimilar()
        getShowVideos()
        checkIfBookmarked()
    }


    private fun checkIfBookmarked() {
        bookmarksRepository
            .isBookmarked(selectedShow.mediaId)
            .distinctUntilChanged()
            .onEach { booked ->
                _state.update { it.copy(isBookmarked = booked) }
            }.launchIn(viewModelScope)
    }

    private fun getShowVideos() {
        viewModelScope.launch {
            val stateVal = _state.value
            val res = showDetailsRepository.getShowVideos(
                type = stateVal.show.mediaType,
                id = stateVal.show.mediaId
            )
            if (res is AppResult.Done) {
                _state.update {
                    it.copy(
                        show = it.show.copy(
                            trailerUrl = res.data ?: it.show.trailerUrl
                        )
                    )
                }
            }
        }
    }

    fun onAction(action: ShowDetailActions) {
        when (action) {
            ShowDetailActions.PaginateSimilar -> getSimilar(paginate = true)
            is ShowDetailActions.AddBookmark -> addBookmark()
            is ShowDetailActions.RemoveBookmark -> removeBookmark()
        }
    }

    private fun removeBookmark() {
        viewModelScope.launch {
            val res = bookmarksRepository.removeFromBookmark(selectedShow.mediaId)
            if (res is AppResult.Error) {
                _event.send(ShowDetailEvent.Error(res.error.errorType.asUiText()))

            }
        }
    }

    private fun addBookmark() {

        viewModelScope.launch {
            val res = bookmarksRepository.addBookmark(selectedShow)
            if (res is AppResult.Error) {
                _event.send(ShowDetailEvent.Error(res.error.errorType.asUiText()))
            }
        }
    }

    private fun getSimilar(
        paginate: Boolean = false,
    ) {

        _state.update { it.copy(loading = !paginate) }

        val stateVal = _state.value
        viewModelScope.launch {
            val res = showDetailsRepository.getSimilarShows(
                media = stateVal.show,
                page = stateVal.page,
                forceRemoteLoad = paginate,
            )
            when (res) {
                is AppResult.Done -> {
                    _state.update {
                        it.copy(
                            page = if (paginate) it.page + 1 else 1,
                            similar = if (paginate) it.similar + res.data else res.data
                        )
                    }
                }

                is AppResult.Error -> Unit

            }
            _state.update { it.copy(loading = false) }
        }
    }
}