package com.mohaberabi.kmovies.app.presetnation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

data class MoviesAppState(
    val navController: NavHostController,
    val snackbarHostState: SnackbarHostState,
    val coroutineScope: CoroutineScope,
)

@Composable
fun rememberMoviesAppState(): MoviesAppState {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val hostState = SnackbarHostState()
    return remember {
        MoviesAppState(
            coroutineScope = scope,
            navController = navController,
            snackbarHostState = hostState
        )
    }
}