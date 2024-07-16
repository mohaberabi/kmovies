package com.mohaberabi.kmovies.features.details.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.kmovies.R
import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.presentation.compose.AppScaffold
import com.mohaberabi.kmovies.core.presentation.compose.CachedImage
import com.mohaberabi.kmovies.core.presentation.compose.EventCollector
import com.mohaberabi.kmovies.core.presentation.compose.MainAppBar
import com.mohaberabi.kmovies.core.presentation.compose.RatingBar
import com.mohaberabi.kmovies.core.presentation.design_system.KmoviesTheme
import com.mohaberabi.kmovies.core.presentation.design_system.Radius
import com.mohaberabi.kmovies.core.presentation.design_system.Spacing
import com.mohaberabi.kmovies.core.util.extensions.shimmerEffect
import com.mohaberabi.kmovies.features.details.presentation.viewmodel.ShowDetailActions
import com.mohaberabi.kmovies.features.details.presentation.viewmodel.ShowDetailEvent
import com.mohaberabi.kmovies.features.details.presentation.viewmodel.ShowDetailState
import com.mohaberabi.kmovies.features.details.presentation.viewmodel.ShowDetailVideModel
import com.mohaberabi.kmovies.features.home.presentation.compose.ShowCard


@Composable
fun ShowDetailScreenRoot(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onShowSnackBar: (String) -> Unit,
    viewModel: ShowDetailVideModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    EventCollector(
        flow = viewModel.event,
    ) { event ->
        if (event is ShowDetailEvent.Error) {
            onShowSnackBar(event.error.asString(context))
        }

    }
    ShowDetailScreen(
        state = state,
        onAction = viewModel::onAction,
        onBackClick = onBackClick,
        modifier = modifier
    )


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDetailScreen(
    modifier: Modifier = Modifier,
    state: ShowDetailState,
    onAction: (ShowDetailActions) -> Unit,
    onBackClick: () -> Unit,
) {


    AppScaffold(
        modifier = modifier,
        fab = {
            FloatingActionButton(
                onClick = {
                    if (state.isBookmarked) {
                        onAction(ShowDetailActions.RemoveBookmark)
                    } else {
                        onAction(ShowDetailActions.AddBookmark)
                    }
                },
            ) {

                Icon(
                    imageVector = if (state.isBookmarked) Icons.Default.Bookmark
                    else Icons.Default.BookmarkBorder, contentDescription = ""
                )
            }
        },
        topAppBar = {
            MainAppBar(
                showBackButton = true,
                onBackClick = onBackClick,
                title = state.show.title
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier.padding(padding),
        ) {


            item {


                Box(
                    contentAlignment = Alignment.BottomStart,
                ) {

                    Box(
                        contentAlignment = Alignment.Center,
                    ) {
                        CachedImage(
                            imageUrl = state.show.backdropFullPath,
                            radius = 0.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                        )
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.LightGray),
                        ) {
                            IconButton(
                                onClick = {

                                },
                            ) {

                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = ""
                                )
                            }
                        }
                    }

                }
            }
            item {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                ) {

                    CachedImage(
                        imageUrl = state.show.posterFullPath,
                        modifier = Modifier
                            .height(225.dp)
                            .width(165.dp)
                            .padding(horizontal = Spacing.xs)
                    )

                    Column(
                        verticalArrangement = Arrangement.Center,
                    ) {


                        Text(
                            text = state.show.title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                            )
                        )

                        Row {
                            RatingBar(
                                rating = state.show.voteAverage / 2,
                            )
                            Text(
                                text = state.show.voteAverage.toString(),
                            )
                        }

                        Text(
                            text = state.show.originCountry.firstOrNull() ?: "",
                        )
                        Text(
                            text = state.show.originCountry.firstOrNull() ?: "",
                        )
                    }


                }

            }

            item {
                Text(
                    text = state.show.overview,
                    modifier = Modifier.padding(Spacing.lg)
                )
            }
            item {
                when {
                    state.loading -> LazyRow {
                        items(8) {
                            Box(
                                modifier = Modifier
                                    .padding(Spacing.sm)
                                    .clip(RoundedCornerShape(Radius.default))
                                    .height(225.dp)
                                    .width(165.dp)
                                    .shimmerEffect()
                            )
                        }
                    }

                    else -> {
                        if (state.similar.isNotEmpty()) {
                            Column {
                                Text(
                                    text = stringResource(R.string.similar),
                                    style = MaterialTheme
                                        .typography
                                        .bodyMedium.copy(fontWeight = FontWeight.Bold)
                                )
                                LazyRow {
                                    items(state.similar) { show ->
                                        ShowCard(
                                            media = show,
                                            modifier = Modifier
                                                .padding(Spacing.sm)
                                                .height(225.dp)
                                                .width(165.dp),
                                            onClick = {},
                                        )
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun PreviewDetailsScreen() {
    KmoviesTheme {


        ShowDetailScreen(
            state = ShowDetailState(
                loading = true,
                show = MediaModel.empty.copy(
                    title = ":ASDASDASDSAd",
                    overview = "alksdnaskjdnasljkdnalskdnaslkdnlkasdnlkasdnlkasdnlaksdnlksadnlkasdnlaksdnaslkdnalskdnlkasdnalksdnlaskdnalskdnaslkdnaslkdnalskdnaslkdnsalkdnalskdnalskdnl"
                )
            ),
            onAction = {},

            ) {

        }
    }
}