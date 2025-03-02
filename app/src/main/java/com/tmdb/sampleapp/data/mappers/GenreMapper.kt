package com.tmdb.sampleapp.data.mappers

import com.tmdb.sampleapp.data.model.genres.GenreResponse
import com.tmdb.sampleapp.domain.model.Genre

class GenreMapper {
    fun map(genresResponseList: List<GenreResponse>): List<Genre> {
        val genres = mutableListOf<Genre>()
        genresResponseList.forEach {
            val genre = Genre(
                id = it.id,
                name = it.genreName
            )
            genres.add(genre)
        }
        return genres
    }
}