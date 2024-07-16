package com.mohaberabi.kmovies.features.layout.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mohaberabi.kmovies.features.bookmark.presentation.navigation.bookmarkScreen
import com.mohaberabi.kmovies.features.home.presentation.navigation.homeScreen
import com.mohaberabi.kmovies.features.layout.presentation.screen.HomeLayoutRoot
import com.mohaberabi.kmovies.features.profile.presentation.navigation.profileScreen
import com.mohaberabi.kmovies.features.search.presentation.navigation.searchScreen
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data object LayoutRoute

@Composable
fun HomeLayoutNavHost(
    rootNavController: NavHostController,
    layoutNavController: NavHostController,
    onShowSnackBar: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = layoutNavController,
        startDestination = AppBottomItem.HOME.name
    ) {
        homeScreen(
            rootNavController = rootNavController,
        )
        searchScreen(
            rootNavController = rootNavController,
        )
        bookmarkScreen(
            rootNavController = rootNavController,
        )
        profileScreen()
    }

}

fun NavGraphBuilder.homeLayout(
    onShowSnackBar: (String) -> Unit,
    rootNavController: NavHostController,
) = composable<LayoutRoute>
{
    val layoutNavController = rememberNavController()
    val bottomRoute = layoutNavController.currentBackStackEntryAsState().value
    HomeLayoutRoot(
        currentRoute = bottomRoute?.destination?.route?.let {
            AppBottomItem.valueOf(it)
        } ?: AppBottomItem.HOME,
        onBottomNavigated = { layoutNavController.navigateBottom(it) },
    ) {
        HomeLayoutNavHost(
            modifier = Modifier.padding(it),
            rootNavController = rootNavController,
            onShowSnackBar = onShowSnackBar,
            layoutNavController = layoutNavController,
        )
    }

}


fun NavController.navigateBottom(
    item: AppBottomItem,
) =
    navigate(item.name) {
        graph.startDestinationRoute?.let {
            popUpTo(it) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }