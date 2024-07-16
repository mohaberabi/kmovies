package com.mohaberabi.kmovies.features.listing.presentation.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.mohaberabi.kmovies.core.util.serializableType
import com.mohaberabi.kmovies.features.details.presentation.navigation.navigateToDetailScreen
import com.mohaberabi.kmovies.features.listing.presentation.screens.ListingScreenRoot
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf


@Serializable
data class ListingRoute(
    val args: ListingNavArgs
) {
    companion object {
        val navTypeMap = mapOf(typeOf<ListingNavArgs>() to serializableType<ListingNavArgs>())
        fun from(savedStateHandle: SavedStateHandle) =
            savedStateHandle.toRoute<ListingRoute>(typeMap = navTypeMap)

    }
}

@Serializable
data class ListingNavArgs(
    val title: String,
    val endpoint: String,
    val trending: Boolean,
)


fun NavGraphBuilder.listingScreen(
    rootNavController: NavHostController
) = composable<ListingRoute>(
    typeMap = ListingRoute.navTypeMap,
) {
    ListingScreenRoot(
        onShowClick = { rootNavController.navigateToDetailScreen(it) },
        onBackClick = { rootNavController.popBackStack() })
}

fun NavController.navigateToListingScreen(
    args: ListingNavArgs,
) = navigate(ListingRoute(args = args))
