package es.santyarbo.mymovies.ui.list

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import es.santyarbo.mymovies.domain.model.Movie

@BindingAdapter("items")
fun RecyclerView.setItems(movies: List<Movie>?) {
    if (movies != null) {
        (adapter as? MoviesAdapter)?.submitList(movies)
    }
}