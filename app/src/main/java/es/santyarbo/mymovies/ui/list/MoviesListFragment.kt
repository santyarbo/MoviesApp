package es.santyarbo.mymovies.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import es.santyarbo.mymovies.R
import es.santyarbo.mymovies.databinding.FragmentMoviesListBinding
import es.santyarbo.mymovies.ui.common.launchAndCollect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    private val viewModel: MoviesListViewModel by viewModels()

    private lateinit var moviesListState: MoviesListState

    private val adapter = MoviesAdapter { moviesListState.onMovieClicked(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesListState = buildMoviesListState()

        val binding = FragmentMoviesListBinding.bind(view).apply {
            recycler.adapter = adapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getMovies().collectLatest { movies ->
                adapter.submitData(movies)
            }
        }
        viewLifecycleOwner.launchAndCollect(viewModel.state) {
            binding.loading = it.loading
            binding.error = it.error?.let(moviesListState::errorToString)
        }
    }

}