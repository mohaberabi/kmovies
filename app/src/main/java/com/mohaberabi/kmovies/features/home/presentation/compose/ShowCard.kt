package com.mohaberabi.kmovies.features.home.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mohaberabi.kmovies.core.data.source.remote.api.MediaApi
import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.presentation.compose.CachedImage
import com.mohaberabi.kmovies.core.presentation.design_system.KmoviesTheme
import com.mohaberabi.kmovies.core.util.constants.ApiConst


@Composable
fun ShowCard(
    modifier: Modifier = Modifier,
    media: MediaModel,
    isPoster: Boolean = false,
    onClick: (MediaModel) -> Unit,
) {
    val url = if (isPoster) {
        media.posterFullPath
    } else {
        media.backdropFullPath
    }
    CachedImage(
        imageUrl = url,
        modifier = modifier,
        onClick = {
            onClick(media)
        }
    )
}


@Preview(showBackground = true)
@Composable
private fun PreviewShowCard() {


    KmoviesTheme {
        ShowCard(
            media = MediaModel.empty,
            onClick = {}
        )
    }
}