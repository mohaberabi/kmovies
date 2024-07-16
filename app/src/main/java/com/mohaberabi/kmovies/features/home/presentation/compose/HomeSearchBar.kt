package com.mohaberabi.kmovies.features.home.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mohaberabi.kmovies.core.presentation.compose.CircleIconButton
import com.mohaberabi.kmovies.core.presentation.design_system.KmoviesTheme
import com.mohaberabi.kmovies.core.presentation.design_system.Spacing


@Composable
fun HomeSearchBar(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    avatar: String = "",
) {

    val letter by remember {
        mutableStateOf(
            avatar.apply {
                if (isNotEmpty()) this[0].uppercase() else ""
            },
        )
    }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(Spacing.xs))
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier.padding(Spacing.sm),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search",
                modifier = Modifier.size(Spacing.lg),
                tint = Color.Gray
            )
            Text(
                text = "Search for movies or tv shows",
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
            )
            Row {

                CircleIconButton(
                    icon = Icons.Default.Bookmarks,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeSearchBar() {

    KmoviesTheme {
        HomeSearchBar()
    }
}