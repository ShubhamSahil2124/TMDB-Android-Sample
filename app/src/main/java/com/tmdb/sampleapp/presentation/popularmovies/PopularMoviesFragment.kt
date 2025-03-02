package com.tmdb.sampleapp.presentation.popularmovies

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.tmdb.sampleapp.databinding.FragmentHomeMoviesBinding
import com.tmdb.sampleapp.domain.model.Movie
import com.tmdb.sampleapp.presentation.GeneralErrorActivity
import com.tmdb.sampleapp.presentation.moviedetails.MovieDetailsActivity
import com.tmdb.sampleapp.presentation.ClickListener
import com.tmdb.sampleapp.presentation.MoviesViewModel
import com.tmdb.sampleapp.presentation.adapter.MoviesRvAdapter
import com.tmdb.sampleapp.presentation.model.ViewState
import com.tmdb.sampleapp.presentation.moviedetails.MovieDetailsActivity.Companion.MOVIE_ID
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularMoviesFragment : Fragment(), ClickListener {

    private lateinit var moviesAdapter: MoviesRvAdapter
    private val moviesViewModel: MoviesViewModel by viewModel()

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
        binding.rvMovies.layoutManager =
            GridLayoutManager(view.context, 2, GridLayoutManager.VERTICAL, false)
        binding.rvMovies.adapter = moviesAdapter


        moviesViewModel.getPopularMovies()

        binding.loading.visibility = View.VISIBLE

        observeGenres()
        observeMovies()
        observeViewState()
    }

    override fun onResume() {
        super.onResume()
        moviesAdapter.notifyDataSetChanged()
    }

    private fun observeMovies(){
        moviesViewModel.movieListLiveData.observe(viewLifecycleOwner) { result ->
            result?.let {
                moviesAdapter.submitList(it)
                binding.loading.visibility = View.GONE
            }
        }
    }

    private fun observeGenres(){
//        moviesViewModel.genreListLiveData.observe(viewLifecycleOwner) { result ->
//            result?.let {
//                genresAdapter.submitList(it)
//            }
//        }
    }

    private fun observeViewState(){
        moviesViewModel.viewStateLiveData.observe(viewLifecycleOwner) { result ->
            if (result == ViewState.GeneralError) {
                val intent = Intent(requireContext(), GeneralErrorActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun openMovieDetails(movieId: Int){
        val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_ID, movieId)
        startActivity(intent)
    }

    override fun loadMoviesWithGenre(genreIds: List<Int>) {
//        moviesViewModel.getMoviesByGenre(genreIds)
    }

    override fun onFavoriteClickedListener(movie: Movie, isChecked: Boolean) {
        if(isChecked){
            movie.isFavorite = true
            moviesViewModel.addToFavorites(movie)
        }else{
            movie.isFavorite = false
            moviesViewModel.removeFromFavorites(movie)
        }
    }

}