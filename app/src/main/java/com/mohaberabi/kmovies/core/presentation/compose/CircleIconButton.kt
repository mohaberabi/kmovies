package com.mohaberabi.kmovies.core.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.mohaberabi.kmovies.core.presentation.design_system.KmoviesTheme
import com.mohaberabi.kmovies.core.presentation.design_system.Spacing


@Composable
fun CircleIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    icon: ImageVector,
) {


    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(Spacing.xlg)
            .background(MaterialTheme.colorScheme.inversePrimary)

    ) {

        IconButton(
            onClick = onClick,
        ) {
            Icon(
                imageVector = icon,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "",
                modifier = Modifier.size(Spacing.md)
            )
        }
    }
}


@Preview(
    showBackground = true,
)
@Composable
private fun PreviewCircleIconButton() {


    KmoviesTheme {
        CircleIconButton(
            icon = Icons.Default.Bookmarks,
        )
    }
}