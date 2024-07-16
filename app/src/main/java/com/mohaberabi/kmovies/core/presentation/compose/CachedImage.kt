package com.mohaberabi.kmovies.core.presentation.compose

import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ImageNotSupported
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.request.ImageResult
import coil.size.Size
import com.mohaberabi.kmovies.core.presentation.design_system.Radius
import com.mohaberabi.kmovies.core.util.extensions.shimmerEffect


@Composable
fun CachedImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    onClick: (() -> Unit)? = null,
    onDone: (Drawable) -> Unit = {},
    radius: Dp = Radius.default,
) {
    CachedImageBody(
        onDone = onDone,
        radius = radius,
        modifier = modifier
            .clickable { onClick?.invoke() },
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(imageUrl)
            .size(Size.ORIGINAL)
            .build()
    )
}

@Composable
fun CachedImage(
    modifier: Modifier = Modifier,
    model: Any?,
    radius: Dp = Radius.default,
) {

    CachedImageBody(
        model = model,
        modifier = modifier
            .clip(RoundedCornerShape(radius)),
    )

}

@Composable
fun CachedImageBody(
    modifier: Modifier = Modifier,
    model: Any?,
    onDone: (Drawable) -> Unit = {},
    radius: Dp = Radius.default,
) {

    var isDone by remember {
        mutableStateOf(false)
    }
    var drawable by remember {

        mutableStateOf<Drawable?>(null)
    }

    LaunchedEffect(
        key1 = isDone,
    ) {
        if (isDone) {
            if (drawable != null) {
                onDone(drawable!!)
            }

        }

    }
    val imageState = rememberAsyncImagePainter(
        model = model,
    ).state
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(radius))
    ) {

        when (imageState) {

            is AsyncImagePainter.State.Loading -> {
                Box(
                    modifier = Modifier
                        .shimmerEffect()
                )
            }

            is AsyncImagePainter.State.Success -> {
                drawable = imageState.result.drawable
                isDone = true
                SubcomposeAsyncImage(
                    model = model,
                    modifier = modifier
                        .clip(RoundedCornerShape(radius)),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                )
            }

            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.ImageNotSupported,
                        contentDescription = ""
                    )
                }

            }
        }
    }
}