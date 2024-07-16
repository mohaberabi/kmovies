package com.mohaberabi.kmovies.features.listing.presentation.compose

import android.provider.MediaStore.Images.Media
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.presentation.design_system.Spacing


@Composable
fun ShowsDetailsGrid(
    modifier: Modifier = Modifier,
    scrollState: LazyGridState = rememberLazyGridState(),
    items: List<MediaModel>,
    onShowClick: (MediaModel) -> Unit,
) {


    LazyVerticalGrid(
        state = scrollState,
        columns = GridCells.Fixed(2),
    ) {
        items(
            items,
        ) { show ->
            ShowDetailCard(
                modifier = Modifier.padding(Spacing.sm),
                onClick = onShowClick,
                show = show,
            )
        }
    }
}