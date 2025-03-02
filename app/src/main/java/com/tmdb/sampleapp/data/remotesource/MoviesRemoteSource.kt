package com.tmdb.sampleapp.data.remotesource

import android.net.Uri
import com.tmdb.sampleapp.data.model.cast.CastListResponse
import com.tmdb.sampleapp.data.model.certification.CertificationListReponse
import com.tmdb.sampleapp.data.model.genres.GenresListResponse
import com.tmdb.sampleapp.data.model.movies.MovieDetailResponse
import com.tmdb.sampleapp.data.model.movies.MoviesListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesRemoteSource {

    @GET("movie/popular")
    fun getPopularMovies(): Single<MoviesListResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: Int): Single<MovieDetailResponse>

    @GET("search/movie")
    fun searchForMovie(@Query("query") movieSearched: Uri): Single<MoviesListResponse>

}