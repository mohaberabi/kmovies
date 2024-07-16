package com.mohaberabi.kmovies.features.home.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mohaberabi.kmovies.core.presentation.compose.ShowShimmer
import com.mohaberabi.kmovies.core.presentation.design_system.KmoviesTheme
import com.mohaberabi.kmovies.core.presentation.design_system.Radius
import com.mohaberabi.kmovies.core.presentation.design_system.Spacing
import com.mohaberabi.kmovies.core.util.extensions.shimmerEffect


@Composable
fun HomeLoadingCompose(
    modifier: Modifier = Modifier,
) {


    LazyColumn(
        modifier = modifier,
    ) {


        homeLoadingItem()
        item {
            ShowShimmer(
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Spacer(modifier = Modifier.height(Spacing.lg))

        }
        repeat(3) {
            homeLoadingItem()

        }
    }

}


fun LazyListScope.homeLoadingItem(

) {

    item {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            ShowShimmer(
                height = Spacing.lg,
                width = Spacing.huge
            )

        }
        LazyRow {

            items(5) {
                ShowShimmer()
            }
        }
        Spacer(modifier = Modifier.height(Spacing.lg))
    }
}


@Preview(
    showBackground = true,
)
@Composable
private fun PreviewHomeLoading() {
    KmoviesTheme {

        HomeLoadingCompose()
    }
}