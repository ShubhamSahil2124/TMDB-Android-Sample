package com.tmdb.sampleapp.data.model.movies

import com.google.gson.annotations.SerializedName

data class MoviesListResponse(
    @SerializedName("results")
    val movieResults: List<MovieResponse>
)
