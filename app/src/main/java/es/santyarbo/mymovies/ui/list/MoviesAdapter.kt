package es.santyarbo.mymovies.ui.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.santyarbo.mymovies.R
import es.santyarbo.mymovies.databinding.RowMovieBinding
import es.santyarbo.mymovies.domain.model.Movie
import es.santyarbo.mymovies.ui.common.basicDiffUtil
import es.santyarbo.mymovies.ui.common.inflate

class MoviesAdapter(private val listener: (Movie) -> Unit) :
    ListAdapter<Movie, MoviesAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.row_movie, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener(movie) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RowMovieBinding.bind(view)
        fun bind(movie: Movie) {
            binding.movie = movie
        }
    }
}