package com.tmdb.sampleapp.data.localsource

import com.tmdb.sampleapp.data.model.movies.MovieResponse
import io.reactivex.Single

interface MovieLocalDataSource {
    fun addToFavorites(movie: MovieResponse): Single<List<MovieResponse>>
    fun removeFromFavorites(movie: MovieResponse): Single<List<MovieResponse>>
    fun getFavoriteMovies(): Single<List<MovieResponse>>
}