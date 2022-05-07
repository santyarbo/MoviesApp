package es.santyarbo.mymovies.ui.detail

import androidx.databinding.BindingAdapter
import es.santyarbo.mymovies.domain.model.Movie

@BindingAdapter("movie")
fun MovieDetailInfoView.updateMovieDetails(movie: Movie?) {
    if (movie != null) {
        setMovie(movie)
    }
}