package com.mohaberabi.kmovies.features.bookmark.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.kmovies.features.bookmark.domain.repository.BookmarksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val bookmarksRepository: BookmarksRepository,
) : ViewModel() {
    val state = bookmarksRepository.getBookmarks()
        .map { BookmarksState.Done(it) as BookmarksState }
        .onStart {
            emit(BookmarksState.Loading)
        }.catch {
            emit(BookmarksState.Error)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            BookmarksState.Loading
        )
}