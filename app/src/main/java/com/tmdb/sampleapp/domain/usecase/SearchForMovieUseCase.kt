package com.tmdb.sampleapp.domain.usecase

import android.net.Uri
import com.tmdb.sampleapp.domain.repository.MoviesRepository

class SearchForMovieUseCase(private val moviesRepository: MoviesRepository) {
    fun executeSearch(movieSearched: Uri) = moviesRepository.searchForMovie(movieSearched)
}