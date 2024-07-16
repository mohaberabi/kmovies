package com.mohaberabi.kmovies.features.bookmark.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mohaberabi.kmovies.features.bookmark.presentation.screen.BookMarkScreenRoot
import com.mohaberabi.kmovies.features.details.presentation.navigation.navigateToDetailScreen
import com.mohaberabi.kmovies.features.layout.presentation.navigation.AppBottomItem
import kotlinx.serialization.Serializable


@Serializable
data object BookMarkRoute


fun NavGraphBuilder.bookmarkScreen(
    rootNavController: NavHostController,
) = composable(
    AppBottomItem.BOOKMARK.name,
) {
    BookMarkScreenRoot(
        onShowClicked = { rootNavController.navigateToDetailScreen(it) }
    )
}