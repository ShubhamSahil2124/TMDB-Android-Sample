package com.tmdb.sampleapp.presentation.model

sealed class ViewState {
    object MovieNotFound: ViewState()
    object GeneralError: ViewState()
}
