package com.mohaberabi.kmovies.features.listing.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.presentation.compose.AppErrorCard
import com.mohaberabi.kmovies.core.presentation.compose.pullrefresh.AppPullRefresh
import com.mohaberabi.kmovies.core.presentation.compose.AppScaffold
import com.mohaberabi.kmovies.core.presentation.compose.MainAppBar
import com.mohaberabi.kmovies.core.presentation.compose.pullrefresh.appScrollable
import com.mohaberabi.kmovies.core.presentation.design_system.Spacing
import com.mohaberabi.kmovies.features.listing.presentation.compose.ListingLoader
import com.mohaberabi.kmovies.features.listing.presentation.compose.ShowDetailCard
import com.mohaberabi.kmovies.features.listing.presentation.compose.ShowsDetailsGrid
import com.mohaberabi.kmovies.features.listing.presentation.viewmodel.ListingActions
import com.mohaberabi.kmovies.features.listing.presentation.viewmodel.ListingState
import com.mohaberabi.kmovies.features.listing.presentation.viewmodel.ListingViewModel


@Composable
fun ListingScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: ListingViewModel = hiltViewModel(),
    onShowClick: (MediaModel) -> Unit,
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ListingScreen(
        state = state,
        modifier = modifier,
        onAction = viewModel::onAction,
        onShowClick = onShowClick,
        onBackClick = onBackClick
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListingScreen(
    modifier: Modifier = Modifier,
    state: ListingState,
    onAction: (ListingActions) -> Unit = {},
    onShowClick: (MediaModel) -> Unit,
    onBackClick: () -> Unit,
) {


    val scrollState = rememberLazyGridState()
    AppScaffold(

        modifier = modifier,
        topAppBar = {


            MainAppBar(
                showBackButton = true,
                onBackClick = onBackClick,
                title = state.title,
            )
        }
    ) {

            padding ->

        Box(
            modifier = Modifier.padding(padding),
        ) {
            AppPullRefresh(
                isRefreshing = state.refreshing,
                onRefresh = { onAction(ListingActions.Refresh) },
                scrollable = scrollState.appScrollable,
                onPaging = { onAction(ListingActions.Paginate) },
            ) {
                when {
                    state.loading || state.refreshing -> ListingLoader()
                    !state.error.isEmpty -> AppErrorCard(
                        errorTitle = state.error,
                        onRetry = {},
                    )

                    else -> ShowsDetailsGrid(
                        scrollState = scrollState,
                        items = state.shows,
                        onShowClick = onShowClick
                    )
                }
            }

        }
    }


}