package es.santyarbo.mymovies.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import es.santyarbo.mymovies.R
import es.santyarbo.mymovies.databinding.FragmentMovieDetailBinding
import es.santyarbo.mymovies.databinding.FragmentMoviesListBinding
import es.santyarbo.mymovies.ui.common.launchAndCollect
import es.santyarbo.mymovies.ui.list.MoviesAdapter

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private val viewModel: MovieDetailViewModel by viewModels()

    private val adapter = SimilarMoviesAdapter { state.onMovieRelatedClicked(it) }

    lateinit var state: MovieDetailState

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMovieDetailBinding.bind(view).apply {
            recycler.adapter = adapter
            movieDetailToolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        }

        state = buildMovieDetailState()

        viewLifecycleOwner.launchAndCollect(viewModel.state) { state ->
            if (state.movie != null) {
                binding.movie = state.movie
            }
            binding.movies = state.movies
            binding.loading = state.loading
        }
    }
}