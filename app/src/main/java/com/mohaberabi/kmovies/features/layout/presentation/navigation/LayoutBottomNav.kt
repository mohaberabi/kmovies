package com.mohaberabi.kmovies.features.layout.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable


@Serializable
enum class AppBottomItem(
    val icon: ImageVector,
) {
    HOME(Icons.Default.Home),
    SEARCH(Icons.Default.Search),
    BOOKMARK(Icons.Default.Bookmarks),
    PROFILE(Icons.Default.AccountCircle)
}


@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    top: AppBottomItem,
    onClick: (AppBottomItem) -> Unit = {},
) {


    BottomAppBar(
        modifier = modifier,
        containerColor = Color.White
    ) {

        AppBottomItem.entries.forEach { item ->

            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    indicatorColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Color.Gray
                ),
                selected = top == item,
                onClick = { onClick(item) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )

                }
            )
        }
    }
}

