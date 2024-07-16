package com.mohaberabi.kmovies.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mohaberabi.kmovies.features.details.presentation.navigation.detailScreen
import com.mohaberabi.kmovies.features.layout.presentation.navigation.LayoutRoute
import com.mohaberabi.kmovies.features.layout.presentation.navigation.homeLayout
import com.mohaberabi.kmovies.features.listing.presentation.navigation.listingScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startRoute: Any = LayoutRoute,
    onShowSnackBar: (String) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startRoute,
    ) {
        homeLayout(
            rootNavController = navController,
            onShowSnackBar = {}
        )
        listingScreen(
            rootNavController = navController,
        )
        detailScreen(
            navController = navController,
            onShowSnackBar = onShowSnackBar,
        )
    }


}