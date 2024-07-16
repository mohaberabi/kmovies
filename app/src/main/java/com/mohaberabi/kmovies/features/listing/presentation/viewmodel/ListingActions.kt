package com.mohaberabi.kmovies.features.listing.presentation.viewmodel


sealed interface ListingActions {
    data object Paginate : ListingActions
    data object Refresh : ListingActions

}