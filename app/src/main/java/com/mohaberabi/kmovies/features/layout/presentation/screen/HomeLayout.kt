package com.mohaberabi.kmovies.features.layout.presentation.screen


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.mohaberabi.kmovies.core.presentation.compose.AppScaffold
import com.mohaberabi.kmovies.features.layout.presentation.navigation.AppBottomBar
import com.mohaberabi.kmovies.features.layout.presentation.navigation.AppBottomItem


@Composable
fun HomeLayoutRoot(
    modifier: Modifier = Modifier,
    onBottomNavigated: (AppBottomItem) -> Unit,
    currentRoute: AppBottomItem,
    dynamicContent: @Composable (PaddingValues) -> Unit,
) {

    HomeLayout(
        modifier = modifier,
        currentRoute = currentRoute,
        onBottomNavigated = onBottomNavigated,
        dynamicContent = dynamicContent
    )
}


@Composable
fun HomeLayout(
    modifier: Modifier = Modifier,
    dynamicContent: @Composable (PaddingValues) -> Unit,
    currentRoute: AppBottomItem,
    onBottomNavigated: (AppBottomItem) -> Unit = {},
) {


    AppScaffold(

        modifier = modifier,

        bottomAppBar = {
            AppBottomBar(
                top = currentRoute,
                onClick = onBottomNavigated,
            )
        }
    ) { padding ->
        dynamicContent(padding)
    }
}