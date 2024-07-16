package com.mohaberabi.kmovies.features.home.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.presentation.compose.AppErrorCard
import com.mohaberabi.kmovies.core.presentation.compose.pullrefresh.AppPullRefresh
import com.mohaberabi.kmovies.core.presentation.compose.AppScaffold
import com.mohaberabi.kmovies.core.presentation.compose.pullrefresh.appScrollable
import com.mohaberabi.kmovies.core.presentation.design_system.KmoviesTheme
import com.mohaberabi.kmovies.features.home.presentation.compose.HomeDoneCompose
import com.mohaberabi.kmovies.features.home.presentation.compose.HomeLoadingCompose
import com.mohaberabi.kmovies.features.home.presentation.viewmodel.HomeActions
import com.mohaberabi.kmovies.features.home.presentation.viewmodel.HomeState
import com.mohaberabi.kmovies.features.home.presentation.viewmodel.HomeViewModel
import com.mohaberabi.kmovies.features.listing.presentation.navigation.ListingNavArgs


@Composable
fun HomeScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onShowMore: (ListingNavArgs) -> Unit,
    onShowClick: (MediaModel) -> Unit

) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction,
        onShowMore = onShowMore,
        onShowClick = onShowClick,
    )
}


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState,
    onAction: (HomeActions) -> Unit,
    onShowMore: (ListingNavArgs) -> Unit,
    onShowClick: (MediaModel) -> Unit
) {

    val scrollState = rememberLazyListState()

    AppScaffold(
    ) { padding ->
        Box(
            modifier = modifier.padding(padding),
        ) {

            AppPullRefresh(
                isRefreshing = state.refreshing,
                scrollable = scrollState.appScrollable,
                onRefresh = {
                    onAction(HomeActions.Refresh)
                },
            ) {
                when {
                    state.loading || state.refreshing -> HomeLoadingCompose()
                    !state.error.isEmpty -> AppErrorCard(
                        onRetry = {
                            onAction(HomeActions.Retry)
                        },
                        errorTitle = state.error,
                    )

                    else -> HomeDoneCompose(
                        data = state.data,
                        lazyListState = scrollState,
                        onShowMore = onShowMore,
                        onShowClick = onShowClick
                    )
                }
            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen(
    modifier: Modifier = Modifier,
) {
    KmoviesTheme {

        HomeScreen(
            state = HomeState(), onAction = {}, onShowClick = {}, onShowMore = {}
        )
    }

}