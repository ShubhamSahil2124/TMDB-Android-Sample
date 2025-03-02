package com.tmdb.sampleapp.data.mappers

import com.tmdb.sampleapp.data.localsource.database.MovieData
import com.tmdb.sampleapp.data.model.movies.MovieResponse

class MovieDataMapper {
    fun map(movie: MovieResponse): MovieData{
            return MovieData(
                imgHome = movie.imgHome,
                id = movie.id,
                title = movie.title,
                rating = movie.rating,
                genreIds = movie.genreIds.joinToString(),
                isFavorite = movie.isFavorite
            )
    }
}