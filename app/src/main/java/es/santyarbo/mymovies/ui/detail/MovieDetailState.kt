package es.santyarbo.mymovies.ui.detail

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import es.santyarbo.mymovies.domain.model.Movie
import kotlinx.coroutines.CoroutineScope

class MovieDetailState(
    private val context: Context,
    private val scope: CoroutineScope,
    private val navController: NavController
) {
    fun onMovieRelatedClicked(movie: Movie) {
        val action = MovieDetailFragmentDirections.actionMovieDetailFragmentSelf(movie)
        navController.navigate(action)
    }
}

fun Fragment.buildMovieDetailState(
    context: Context = requireContext(),
    scope: CoroutineScope = viewLifecycleOwner.lifecycleScope,
    navController: NavController = findNavController()
) = MovieDetailState(context, scope, navController)