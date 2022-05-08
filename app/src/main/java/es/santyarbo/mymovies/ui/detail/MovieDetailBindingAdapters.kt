package es.santyarbo.mymovies.ui.detail

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import es.santyarbo.mymovies.domain.model.Movie

@BindingAdapter("movie")
fun MovieDetailInfoView.updateMovieDetails(movie: Movie?) {
    if (movie != null) {
        setMovie(movie)
    }
}

@BindingAdapter("similar_movies")
fun RecyclerView.setItems(movies: List<Movie>?) {
    if (movies != null) {
        (adapter as? SimilarMoviesAdapter)?.submitList(movies)
    }
}