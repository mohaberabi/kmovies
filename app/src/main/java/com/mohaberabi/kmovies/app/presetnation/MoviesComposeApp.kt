package com.mohaberabi.kmovies.app.presetnation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mohaberabi.kmovies.core.presentation.compose.AppScaffold
import com.mohaberabi.kmovies.core.presentation.design_system.KmoviesTheme
import com.mohaberabi.kmovies.core.presentation.navigation.AppNavHost
import kotlinx.coroutines.launch


@Composable
fun MoviesComposeApp(
    modifier: Modifier = Modifier,
    moviesAppState: MoviesAppState,
) {
    val snackbarHostState = moviesAppState.snackbarHostState
    val scope = moviesAppState.coroutineScope
    KmoviesTheme {
        AppScaffold(
            snackBarHostState = snackbarHostState,

            ) {
            AppNavHost(
                modifier = modifier,
                navController = moviesAppState.navController,
                onShowSnackBar = {
                    scope.launch {
                        snackbarHostState.showSnackbar(it)
                    }
                }
            )
        }


    }

}