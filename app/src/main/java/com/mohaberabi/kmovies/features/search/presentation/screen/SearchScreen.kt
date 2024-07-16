package com.mohaberabi.kmovies.features.search.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.jetmart.core.presentation.compose.PrimaryTextField
import com.mohaberabi.kmovies.R
import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.presentation.design_system.KmoviesTheme
import com.mohaberabi.kmovies.core.presentation.design_system.Radius
import com.mohaberabi.kmovies.core.presentation.design_system.Spacing
import com.mohaberabi.kmovies.features.listing.presentation.compose.ShowsDetailsGrid
import com.mohaberabi.kmovies.features.search.presentation.viewmodel.SearchActions
import com.mohaberabi.kmovies.features.search.presentation.viewmodel.SearchViewModel
import kotlin.math.roundToInt


@Composable
fun SearchScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onShowClick: (MediaModel) -> Unit,
) {

    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val items by viewModel.shows.collectAsStateWithLifecycle()
    SearchScreen(
        modifier = modifier,
        searchQuery = searchQuery,
        foundShows = items,
        onShowClick = onShowClick,
        onAction = viewModel::onAction
    )
}


@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchQuery: String,
    foundShows: List<MediaModel>,
    onShowClick: (MediaModel) -> Unit,
    onAction: (SearchActions) -> Unit,
) {
    val toolbarHeightPx = with(LocalDensity.current) {
        Radius.xxxL.roundToPx().toFloat()
    }

    val toolbarOffsetHeightPx = remember {
        mutableFloatStateOf(0f)
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.floatValue + delta
                toolbarOffsetHeightPx.floatValue = newOffset.coerceIn(-toolbarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {


        Column(
            modifier = Modifier
                .padding(Spacing.lg)
        ) {
            Text(
                text = stringResource(R.string.search),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )
            PrimaryTextField(
                value = searchQuery,
                placeHolder = stringResource(R.string.search_for_movies_or_tv_shows),
                onChanged = {
                    onAction(SearchActions.SearchChanged(it))
                }
            )


            ShowsDetailsGrid(
                items = foundShows,
                onShowClick = onShowClick
            )


        }

    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun PreviewSearchScreen() {

    KmoviesTheme {
        SearchScreen(
            searchQuery = "",

            foundShows = emptyList(),
            onShowClick = {},
            onAction = {}
        )
    }
}