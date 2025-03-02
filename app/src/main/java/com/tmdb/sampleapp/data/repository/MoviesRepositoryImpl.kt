package com.tmdb.sampleapp.data.repository

import android.net.Uri
import com.tmdb.sampleapp.data.base.Network
import com.tmdb.sampleapp.data.localsource.MovieLocalDataSource
import com.tmdb.sampleapp.data.mappers.*
import com.tmdb.sampleapp.data.remotesource.MoviesRemoteSource
import com.tmdb.sampleapp.domain.model.*
import com.tmdb.sampleapp.domain.repository.MoviesRepository
import io.reactivex.Single

class MoviesRepositoryImpl(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieMapper: MovieMapper,
    private val movieDetailMapper: MovieDetailMapper,
): MoviesRepository {
    private val moviesRemoteSource: MoviesRemoteSource = Network.getMoviesRemoteSource()

    override fun getPopularMovies(): Single<List<Movie>> {
        return moviesRemoteSource
            .getPopularMovies()
            .flatMap { movieResponseList ->
                movieLocalDataSource
                    .getFavoriteMovies()
                    .map { favoriteMovieList ->
                        movieResponseList.movieResults.forEach { movieResponse ->
                            val result = favoriteMovieList.any { favoriteMovie ->
                                favoriteMovie.id == movieResponse.id
                            }
                            movieResponse.isFavorite = result
                        }
                        movieResponseList.movieResults
                    }
            }
            .map {
                movieMapper.map(it)
            }
    }

    override fun getMovieDetails(movieId: Int): Single<MovieDetail> {
        return moviesRemoteSource
            .getMovieDetails(movieId)
            .flatMap { movieResponse ->
                movieLocalDataSource
                    .getFavoriteMovies()
                    .map { favoriteMovieList ->
                        val result = favoriteMovieList.any { favoriteMovie ->
                            favoriteMovie.id == movieResponse.id
                        }
                        movieResponse.isFavorite = result
                        movieResponse
                    }
            }
            .map{
                movieDetailMapper.map(it)
        }
    }


    override fun searchForMovie(movieSearched: Uri): Single<List<Movie>> {
        return moviesRemoteSource
            .searchForMovie(movieSearched)
            .flatMap { movieResponseList ->
                movieLocalDataSource
                    .getFavoriteMovies()
                    .map { favoriteMovieList ->
                        movieResponseList.movieResults.forEach { movieResponse ->
                            val result = favoriteMovieList.any { favoriteMovie ->
                                favoriteMovie.id == movieResponse.id
                            }
                            movieResponse.isFavorite = result
                        }
                        movieResponseList.movieResults
                    }
            }
            .map {
                movieMapper.map(it)
            }
    }

    companion object {
        private const val BR = "BR"
    }

}