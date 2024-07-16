package com.mohaberabi.kmovies.features.home.presentation.compose

import android.provider.MediaStore.Images.Media
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mohaberabi.kmovies.R
import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.presentation.design_system.Spacing
import com.mohaberabi.kmovies.features.listing.presentation.navigation.ListingNavArgs


@Composable
fun ShowLazyRow(
    modifier: Modifier = Modifier,
    medias: List<MediaModel>,
    title: String,
    onShowMoreClick: (ListingNavArgs) -> Unit,
    trending: Boolean = false,
    onShowClick: (MediaModel) -> Unit,
) {


    Column(
        modifier = modifier,
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = Spacing.sm
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            )
            if (medias.isNotEmpty()) {
                Text(
                    modifier = Modifier.clickable {
                        onShowMoreClick(
                            ListingNavArgs(
                                endpoint = medias.first().mediaType,
                                title = title,
                                trending = trending
                            )
                        )
                    },
                    text = stringResource(id = R.string.show_more),
                    style = MaterialTheme.typography.bodyLarge,
                )

            }

        }
        Spacer(
            modifier = Modifier.height(Spacing.md),
        )
        LazyRow {
            items(medias) { media ->
                ShowCard(
                    onClick = onShowClick,
                    media = media,
                    modifier = Modifier
                        .padding(horizontal = Spacing.xs)
                        .width(150.dp)
                        .height(225.dp)
                )
            }
        }
    }
}