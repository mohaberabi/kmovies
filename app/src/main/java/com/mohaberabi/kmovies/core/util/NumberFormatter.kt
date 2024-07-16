package com.mohaberabi.kmovies.core.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember


class NumberFormatter {

    companion object {
        const val READABLE_TIME = "%02d"
    }

    operator fun invoke(
        vararg values: Number,
        pattern: String,
    ): String = String.format(pattern, values)
}


@Composable
fun rememberNumberFormatter(): NumberFormatter {
    return remember {
        NumberFormatter()
    }
}