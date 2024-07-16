package com.mohaberabi.kmovies.features.search.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mohaberabi.kmovies.features.details.presentation.navigation.navigateToDetailScreen
import com.mohaberabi.kmovies.features.layout.presentation.navigation.AppBottomItem
import com.mohaberabi.kmovies.features.search.presentation.screen.SearchScreenRoot


fun NavGraphBuilder.searchScreen(
    rootNavController: NavController,
) = composable(
    AppBottomItem.SEARCH.name
) {

    SearchScreenRoot(
        onShowClick = {
            rootNavController.navigateToDetailScreen(it)
        }
    )
}