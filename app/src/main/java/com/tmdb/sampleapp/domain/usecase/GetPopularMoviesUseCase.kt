package com.tmdb.sampleapp.domain.usecase

import com.tmdb.sampleapp.domain.repository.MoviesRepository

class GetPopularMoviesUseCase(private val moviesRepository: MoviesRepository) {
    fun execute() = moviesRepository.getPopularMovies()
}