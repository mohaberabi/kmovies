package com.mohaberabi.kmovies.features.home.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mohaberabi.kmovies.features.details.presentation.navigation.navigateToDetailScreen
import com.mohaberabi.kmovies.features.home.presentation.screen.HomeScreenRoot
import com.mohaberabi.kmovies.features.layout.presentation.navigation.AppBottomItem
import com.mohaberabi.kmovies.features.listing.presentation.navigation.navigateToListingScreen
import kotlinx.serialization.Serializable


fun NavGraphBuilder.homeScreen(
    rootNavController: NavHostController,
) = composable(AppBottomItem.HOME.name) {
    HomeScreenRoot(
        onShowMore = { rootNavController.navigateToListingScreen(args = it) },
        onShowClick = { rootNavController.navigateToDetailScreen(it) }
    )
}