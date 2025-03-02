package com.tmdb.sampleapp.domain.repository

import android.net.Uri
import com.tmdb.sampleapp.domain.model.*
import io.reactivex.Single

interface MoviesRepository {
    fun getPopularMovies(): Single<List<Movie>>
    fun getMovieDetails(movieId: Int): Single<MovieDetail>
    fun searchForMovie(movieSearched: Uri): Single<List<Movie>>
}