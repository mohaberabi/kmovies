package com.mohaberabi.kmovies.features.details.presentation.viewmodel

import com.mohaberabi.kmovies.core.util.UiText

interface ShowDetailEvent {


    data class Error(val error: UiText) : ShowDetailEvent
}