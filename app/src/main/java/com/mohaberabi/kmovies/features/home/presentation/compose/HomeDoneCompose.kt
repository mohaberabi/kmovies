package com.mohaberabi.kmovies.features.home.presentation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.presentation.compose.CachedImage
import com.mohaberabi.kmovies.core.presentation.compose.CarouselSlider
import com.mohaberabi.kmovies.core.presentation.design_system.Spacing
import com.mohaberabi.kmovies.features.home.domain.model.HomeScreenData
import com.mohaberabi.kmovies.features.listing.presentation.navigation.ListingNavArgs


@Composable
fun HomeDoneCompose(
    modifier: Modifier = Modifier,
    data: HomeScreenData,
    lazyListState: LazyListState,
    onShowMore: (ListingNavArgs) -> Unit,
    onShowClick: (MediaModel) -> Unit

) {


    LazyColumn(
        modifier = modifier,
        state = lazyListState
    ) {

        item {
            ShowLazyRow(
                medias = data.trending,
                trending = true,
                title = "Trending",
                onShowMoreClick = onShowMore,
                onShowClick = onShowClick
            )
        }
        item {
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = Spacing.sm,
                        vertical = Spacing.md
                    )
            ) {
                Text(
                    text = "Special",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                )

                CarouselSlider<MediaModel>(
                    items = data.special,
                    content = { item ->
                        CachedImage(
                            imageUrl = item.backdropFullPath,
                            modifier = Modifier
                                .padding(horizontal = Spacing.xs)
                                .fillMaxWidth()
                                .height(225.dp)
                        )
                    },
                )
            }
        }
        item {
            ShowLazyRow(
                medias = data.movies,
                modifier = Modifier.padding(vertical = Spacing.sm),
                title = "Movies",
                onShowMoreClick = onShowMore,
                onShowClick = onShowClick


            )
        }
        item {
            ShowLazyRow(
                modifier = Modifier.padding(vertical = Spacing.sm),
                medias = data.tvs,
                title = "Tv shows",
                onShowMoreClick = onShowMore,
                onShowClick = onShowClick
            )
        }
    }

}