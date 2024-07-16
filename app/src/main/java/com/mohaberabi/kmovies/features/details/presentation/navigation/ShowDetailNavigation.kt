package com.mohaberabi.kmovies.features.details.presentation.navigation

import android.provider.MediaStore.Images.Media
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.util.serializableType
import com.mohaberabi.kmovies.features.details.presentation.screen.ShowDetailScreenRoot
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf


@Serializable
data class ShowDetailRoute(
    val showJson: String,
) {
    companion object {
        fun from(savedStateHandle: SavedStateHandle): ShowDetailRoute =
            savedStateHandle.toRoute<ShowDetailRoute>()
    }


}

fun NavGraphBuilder.detailScreen(
    navController: NavHostController,
    onShowSnackBar: (String) -> Unit,
) =
    composable<ShowDetailRoute>(
    ) {
        ShowDetailScreenRoot(
            onBackClick = { navController.popBackStack() },
            onShowSnackBar = onShowSnackBar,
        )
    }

fun NavController.navigateToDetailScreen(show: MediaModel) =
    navigate(ShowDetailRoute(Json.encodeToString(show)))