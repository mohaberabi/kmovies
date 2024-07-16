package com.mohaberabi.kmovies.features.listing.presentation.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.kmovies.core.presentation.design_system.KmoviesTheme
import com.mohaberabi.kmovies.core.presentation.design_system.Radius
import com.mohaberabi.kmovies.core.presentation.design_system.Spacing
import com.mohaberabi.kmovies.core.util.extensions.shimmerEffect


@Composable
fun ListingLoader(
    modifier: Modifier = Modifier,
) {


    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
    ) {


        items(12) {
            Box(
                modifier = Modifier
                    .padding(Spacing.sm)
                    .clip(RoundedCornerShape(Radius.default))
                    .shimmerEffect()
                    .height(220.dp)
                    .width(90.dp),
            )
        }

    }

}


@Preview(
    showBackground = true,
)
@Composable
private fun PreviewListingLoader() {
    KmoviesTheme {

        ListingLoader()
    }
}