package com.tmdb.sampleapp.data.mappers

import com.tmdb.sampleapp.data.localsource.database.MovieData
import com.tmdb.sampleapp.data.model.movies.MovieResponse
import com.tmdb.sampleapp.domain.model.Movie

class MovieResponseMapper {
    fun map(movie: Movie): MovieResponse {
            return MovieResponse(
                imgHome = movie.imgHome,
                id = movie.id,
                title = movie.title,
                rating = movie.rating,
                genreIds = movie.genreIds,
                isFavorite = movie.isFavorite
            )
    }

    fun map(movie: MovieData): MovieResponse {
        return MovieResponse(
            imgHome = movie.imgHome,
            id = movie.id,
            title = movie.title,
            rating = movie.rating,
            genreIds = movie.genreIds.asIntList(),
            isFavorite = movie.isFavorite
        )
    }

    private fun String.asIntList(): List<Int> {
        return this.split(",").map { it.trim().toInt() }
    }
}