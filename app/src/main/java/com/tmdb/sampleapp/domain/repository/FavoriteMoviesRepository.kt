package com.tmdb.sampleapp.domain.repository

import com.tmdb.sampleapp.domain.model.Movie
import io.reactivex.Single

interface FavoriteMoviesRepository {
    fun addToFavorites(movie: Movie): Single<List<Movie>>
    fun removeFromFavorites(movie: Movie): Single<List<Movie>>
    fun getFavoriteMovies(): Single<List<Movie>>
}