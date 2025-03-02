package com.tmdb.sampleapp.di

import com.tmdb.sampleapp.data.localsource.database.MovieLocalDataSourceImpl
import com.tmdb.sampleapp.data.mappers.CastMapper
import com.tmdb.sampleapp.data.mappers.CertificationMapper
import com.tmdb.sampleapp.data.mappers.GenreMapper
import com.tmdb.sampleapp.data.mappers.MovieDataMapper
import com.tmdb.sampleapp.data.mappers.MovieDetailMapper
import com.tmdb.sampleapp.data.mappers.MovieMapper
import com.tmdb.sampleapp.data.mappers.MovieResponseMapper
import com.tmdb.sampleapp.data.repository.FavoriteMoviesRepositoryImpl
import com.tmdb.sampleapp.data.repository.MoviesRepositoryImpl
import com.tmdb.sampleapp.domain.repository.FavoriteMoviesRepository
import com.tmdb.sampleapp.domain.repository.MoviesRepository
import com.tmdb.sampleapp.domain.usecase.FavoriteMoviesUseCase

import com.tmdb.sampleapp.domain.usecase.GetMovieDetailsUseCase
import com.tmdb.sampleapp.domain.usecase.GetPopularMoviesUseCase
import com.tmdb.sampleapp.domain.usecase.SearchForMovieUseCase
import com.tmdb.sampleapp.presentation.MoviesViewModel
import com.tmdb.sampleapp.presentation.moviedetails.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModules = module {
    factory { MovieDataMapper() }
    factory { MovieResponseMapper() }
    factory { MovieMapper() }

    factory<MoviesRepository> {
        MoviesRepositoryImpl(
            movieLocalDataSource = MovieLocalDataSourceImpl(
                movieDataMapper = get(),
                movieResponseMapper = get()
            ),
            movieMapper = get(),
            movieDetailMapper = MovieDetailMapper()
        )
    }

    factory<FavoriteMoviesRepository> {
        FavoriteMoviesRepositoryImpl(
            movieLocalDataSource = MovieLocalDataSourceImpl(
                movieDataMapper = get(),
                movieResponseMapper = get()
            ),
            movieMapper = get(),
            movieResponseMapper = get()
        )
    }
}

val domainModules = module {
    factory { FavoriteMoviesUseCase(favoriteMoviesRepository = get()) }

    factory { GetMovieDetailsUseCase(movieRepository = get()) }

    factory { GetPopularMoviesUseCase(moviesRepository = get()) }
    factory { SearchForMovieUseCase(moviesRepository = get()) }
}

val presentationModules = module {
    single {
        MoviesViewModel(
            favoriteMoviesUseCase = get(),
            getPopularMoviesUseCase = get(),
            searchForMoviesUseCase = get()
        )
    }

    viewModel { MovieDetailsViewModel(getMovieDetailsUseCase = get()) }
}