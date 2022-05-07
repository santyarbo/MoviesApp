package es.santyarbo.mymovies.ui.list

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import es.santyarbo.mymovies.R
import es.santyarbo.mymovies.domain.model.Movie
import es.santyarbo.mymovies.domain.model.Error
import kotlinx.coroutines.CoroutineScope

class MoviesListState(
    private val context: Context,
    private val scope: CoroutineScope,
    private val navController: NavController
) {

    fun onMovieClicked(movie: Movie) {
        val action = MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailFragment(movie)
        navController.navigate(action)
    }

    fun errorToString(error: Error) = when (error) {
        is Error.Connectivity -> context.getString(R.string.connectivity_error)
        is Error.Server -> context.getString(R.string.server_error) + " " + error.code
        is Error.Unknown -> context.getString(R.string.unknown_error) + " " + error.message
    }
}

fun Fragment.buildMoviesListState(
    context: Context = requireContext(),
    scope: CoroutineScope = viewLifecycleOwner.lifecycleScope,
    navController: NavController = findNavController()
) = MoviesListState(context, scope, navController)