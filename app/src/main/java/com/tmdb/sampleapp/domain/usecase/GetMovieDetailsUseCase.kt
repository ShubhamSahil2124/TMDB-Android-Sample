package com.tmdb.sampleapp.domain.usecase

import com.tmdb.sampleapp.domain.repository.MoviesRepository

class GetMovieDetailsUseCase(private val movieRepository: MoviesRepository) {
    fun executeMovie(movieId: Int) = movieRepository.getMovieDetails(movieId)
}