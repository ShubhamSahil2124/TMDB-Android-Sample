package com.tmdb.sampleapp.domain.usecase

import com.tmdb.sampleapp.domain.repository.FavoriteMoviesRepository
import com.tmdb.sampleapp.domain.model.Movie

class FavoriteMoviesUseCase(private val favoriteMoviesRepository: FavoriteMoviesRepository) {

    fun getFavoriteMovies() = favoriteMoviesRepository.getFavoriteMovies()
    fun addFavoriteMovie(movie: Movie) = favoriteMoviesRepository.addToFavorites(movie)
    fun removeFavoriteMovie(movie: Movie) = favoriteMoviesRepository.removeFromFavorites(movie)
}