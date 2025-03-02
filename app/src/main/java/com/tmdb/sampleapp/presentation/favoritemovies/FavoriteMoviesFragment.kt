package com.tmdb.sampleapp.presentation.favoritemovies

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tmdb.sampleapp.databinding.FragmentHomeMoviesBinding
import com.tmdb.sampleapp.domain.model.Movie
import com.tmdb.sampleapp.presentation.ClickListener
import com.tmdb.sampleapp.presentation.MoviesViewModel

import com.tmdb.sampleapp.presentation.adapter.MoviesRvAdapter
import com.tmdb.sampleapp.presentation.moviedetails.MovieDetailsActivity
import com.tmdb.sampleapp.presentation.moviedetails.MovieDetailsActivity.Companion.MOVIE_ID
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class FavoriteMoviesFragment : Fragment(), ClickListener {

    private lateinit var moviesAdapter: MoviesRvAdapter

    private val  viewModelFavorites: MoviesViewModel by viewModel()
    private lateinit var binding: FragmentHomeMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        moviesAdapter = MoviesRvAdapter(context = view.context, listener = this)
        binding.rvMovies.adapter = moviesAdapter
        observeFavoriteMovies()
    }

    override fun onResume() {
        super.onResume()
        viewModelFavorites.getFavoriteMovies()
    }



    private fun observeFavoriteMovies() {
        viewModelFavorites.favoriteMoviesLiveData.observe(viewLifecycleOwner) { result ->
            result?.let {
                moviesAdapter.submitList(it)
                binding.loading.visibility = View.GONE
            }
        }
    }

    override fun onFavoriteClickedListener(movie: Movie, isChecked: Boolean) {
        if (!isChecked) {
            movie.isFavorite = false
            viewModelFavorites.removeFromFavorites(movie)
        }
    }

    override fun openMovieDetails(movieId: Int) {
        val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_ID, movieId)
        startActivity(intent)
    }

    override fun loadMoviesWithGenre(genreIds: List<Int>) {
        viewModelFavorites.favoriteMoviesLiveData.observe(viewLifecycleOwner) { result ->
            result?.let { movies ->
                val movieList = mutableListOf<Movie>()
                movies.forEach { movie ->
                    if (movie.genreIds.containsAll(genreIds)) {
                        movieList.add(movie)
                    }
                }
                moviesAdapter.submitList(movieList)
            }
        }
    }


}