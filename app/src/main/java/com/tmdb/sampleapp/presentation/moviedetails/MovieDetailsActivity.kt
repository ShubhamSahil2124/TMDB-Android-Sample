package com.tmdb.sampleapp.presentation.moviedetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.tmdb.sampleapp.R
import com.tmdb.sampleapp.data.base.Constants
import com.tmdb.sampleapp.databinding.ActivityMovieDetailsBinding
import com.tmdb.sampleapp.domain.model.MovieDetail
import com.tmdb.sampleapp.presentation.GeneralErrorActivity

import com.tmdb.sampleapp.presentation.model.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class MovieDetailsActivity : AppCompatActivity() {


    private val viewModel: MovieDetailsViewModel by viewModel()
    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.extras?.getInt(MOVIE_ID)
        movieId?.let {
            viewModel.getMovieDetails(it)
        }

        observeMovieDetails()
        observeViewState()

        binding.returnBtn.setOnClickListener { finish() }
    }

    private fun observeViewState(){
        viewModel.viewStateLiveData.observe(this) { result ->
            if (result == ViewState.GeneralError) {
                val intent = Intent(this, GeneralErrorActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun observeMovieDetails(){
        viewModel.movieLiveData.observe(this) { result ->
            result?.let {
                showMovie(it)
            }
        }
    }

    private fun showMovie(movie: MovieDetail){



        movie.backdropPath?.let {
            Glide.with(this)
                .load(Constants.BASE_URL_IMAGE.value + movie.backdropPath)
                .into(binding.posterMovie)
        }
        movie.overview?.let {
            binding.movieSynopsis.text = it
        }
        movie.runtime?.let {
            binding.movieDuration.text = movie.getRuntime()
        }

        binding.movieTitle.text = movie.title
        binding.ratingMovieInfoAct.text = movie.getRating()

        if (movie.isFavorite) {
            binding.favBtnMovieInfo.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.favBtnMovieInfo.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }

        binding.movieYear.text = movie.getReleaseYear()



    }


    companion object {
        const val MOVIE_ID = "MOVIE_ID"
    }
}
