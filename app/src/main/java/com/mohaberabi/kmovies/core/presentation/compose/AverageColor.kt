package com.mohaberabi.kmovies.core.presentation.compose

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils


fun Bitmap.averageColor(
): Color {
    var averageColor = Color.Transparent
    val bitmap = this
    val compaitable = bitmap.copy(Bitmap.Config.ARGB_8888, false)
    val pixels = IntArray(compaitable.width * compaitable.height)
    compaitable.getPixels(
        pixels,
        0,
        compaitable.width,
        0,
        0,
        compaitable.width,
        compaitable.height
    )
    val reds = pixels.sumOf { android.graphics.Color.red(it) }
    val blues = pixels.sumOf { android.graphics.Color.blue(it) }
    val greens = pixels.sumOf { android.graphics.Color.green(it) }
    val size = pixels.size
    val avgRed = reds / size
    val avgBlues = blues / size
    val avgGreens = greens / size
    averageColor = Color(avgRed, avgBlues, avgGreens)
    val hsl = FloatArray(3)
    ColorUtils.colorToHSL(averageColor.toArgb(), hsl)
    val darkness = hsl[2] - 0.1f
    val darkerColor = ColorUtils.HSLToColor(floatArrayOf(hsl[0], hsl[1], darkness))
    return Color(darkerColor)
}