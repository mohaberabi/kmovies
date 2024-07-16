package com.mohaberabi.kmovies.features.bookmark.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.kmovies.R
import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.presentation.compose.AppErrorCard
import com.mohaberabi.kmovies.core.presentation.compose.ShowShimmerGrid
import com.mohaberabi.kmovies.core.presentation.design_system.Spacing
import com.mohaberabi.kmovies.features.bookmark.presentation.viewmodel.BookmarksState
import com.mohaberabi.kmovies.features.bookmark.presentation.viewmodel.BookmarksViewModel
import com.mohaberabi.kmovies.features.listing.presentation.compose.ShowsDetailsGrid


@Composable
fun BookMarkScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: BookmarksViewModel = hiltViewModel(),
    onShowClicked: (MediaModel) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    BookMarkScreen(
        modifier = modifier,
        state = state,
        onShowClicked = onShowClicked
    )
}


@Composable
fun BookMarkScreen(
    modifier: Modifier = Modifier,
    state: BookmarksState,
    onShowClicked: (MediaModel) -> Unit,
) {

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier.padding(Spacing.lg)
    ) {

        Text(
            text = stringResource(R.string.bookmarks),
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(Spacing.sm))

        when (state) {
            is BookmarksState.Done -> ShowsDetailsGrid(
                items = state.bookmarks,
                onShowClick = onShowClicked
            )

            BookmarksState.Error -> AppErrorCard(errorTitle = stringResource(R.string.something_went_wrong))
            BookmarksState.Loading -> ShowShimmerGrid()
        }


    }
}