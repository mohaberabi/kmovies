package com.mohaberabi.kmovies.core.presentation.compose

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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mohaberabi.kmovies.core.presentation.design_system.Radius
import com.mohaberabi.kmovies.core.presentation.design_system.Spacing
import com.mohaberabi.kmovies.core.util.extensions.shimmerEffect

@Composable
fun ShowShimmer(
    modifier: Modifier = Modifier,
    width: Dp = 150.dp,
    height: Dp = 200.dp,
) {
    Box(
        modifier = modifier
            .padding(Spacing.sm)
            .clip(RoundedCornerShape(Radius.sm))
            .shimmerEffect()
            .height(height)
            .width(width)
    )
}


@Composable
fun ShowShimmerGrid(
    modifier: Modifier = Modifier,
) {


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
    ) {

        items(12) {
            ShowShimmer()
        }
    }

}