package com.mohaberabi.kmovies.features.profile.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mohaberabi.kmovies.features.layout.presentation.navigation.AppBottomItem
import com.mohaberabi.kmovies.features.profile.presentation.screen.ProfileScreen
import kotlinx.serialization.Serializable


fun NavGraphBuilder.profileScreen(

) = composable(
    AppBottomItem.PROFILE.name
) {
    ProfileScreen()
}