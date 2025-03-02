package com.tmdb.sampleapp.data.repository

import com.tmdb.sampleapp.data.localsource.MovieLocalDataSource
import com.tmdb.sampleapp.data.mappers.MovieMapper
import com.tmdb.sampleapp.data.mappers.MovieResponseMapper
import com.tmdb.sampleapp.domain.model.Movie
import com.tmdb.sampleapp.domain.repository.FavoriteMoviesRepository
import io.reactivex.Single

class FavoriteMoviesRepositoryImpl(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieMapper: MovieMapper,
    private val movieResponseMapper: MovieResponseMapper
): FavoriteMoviesRepository {


    override fun addToFavorites(movie: Movie): Single<List<Movie>> {
        val movieMapped = movieResponseMapper.map(movie)
        return movieLocalDataSource
            .addToFavorites(movieMapped)
            .map{
                movieMapper.map(it)
            }
    }

    override fun removeFromFavorites(movie: Movie): Single<List<Movie>> {
        val movieMapped = movieResponseMapper.map(movie)
        return movieLocalDataSource
            .removeFromFavorites(movieMapped)
            .map {
                movieMapper.map(it)
            }
    }

    override fun getFavoriteMovies(): Single<List<Movie>> {
        return movieLocalDataSource
            .getFavoriteMovies()
            .map {
                movieMapper.map(it)
            }
    }
}