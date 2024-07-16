package com.mohaberabi.kmovies.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.hilt.navigation.compose.hiltViewModel
import com.mohaberabi.kmovies.app.presetnation.MoviesComposeApp
import com.mohaberabi.kmovies.app.presetnation.rememberMoviesAppState
import com.mohaberabi.kmovies.features.home.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val state = rememberMoviesAppState()
            MoviesComposeApp(
                moviesAppState = state,
            )
        }
    }
}