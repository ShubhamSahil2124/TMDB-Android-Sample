package com.tmdb.sampleapp.presentation

import com.tmdb.sampleapp.domain.model.Movie

internal interface ClickListener {
    fun openMovieDetails(movieId: Int)
    fun loadMoviesWithGenre(genreIds: List<Int>)
    fun onFavoriteClickedListener(movie: Movie, isChecked: Boolean)
}