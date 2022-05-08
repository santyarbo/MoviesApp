package es.santyarbo.mymovies.ui.detail

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.santyarbo.mymovies.R
import es.santyarbo.mymovies.databinding.RowSimilarMovieBinding
import es.santyarbo.mymovies.domain.model.Movie
import es.santyarbo.mymovies.ui.common.basicDiffUtil
import es.santyarbo.mymovies.ui.common.inflate

class SimilarMoviesAdapter(private val listener: (Movie) -> Unit) :
    ListAdapter<Movie, SimilarMoviesAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.row_similar_movie, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener(movie) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RowSimilarMovieBinding.bind(view)
        fun bind(movie: Movie) {
            binding.movie = movie
        }
    }
}