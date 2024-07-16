package com.mohaberabi.kmovies.core.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.mohaberabi.kmovies.R


sealed interface UiText {


    companion object {
        val unKnown = UiText.StringResource(R.string.unknown_error)
    }

    data object Empty : UiText


    data class StringResource(@StringRes val id: Int) : UiText
    data class Dynamic(val value: String) : UiText


    fun asString(context: Context): String = when (this) {
        is Dynamic -> this.value
        Empty -> ""
        is StringResource -> context.getString(this.id)
    }

    @Composable
    fun asString(): String = when (this) {
        is Dynamic -> this.value
        Empty -> ""
        is StringResource -> LocalContext.current.getString(this.id)
    }


    val isEmpty: Boolean
        get() = this is Empty

}