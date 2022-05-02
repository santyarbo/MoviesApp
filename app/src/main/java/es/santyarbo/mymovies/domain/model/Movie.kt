package es.santyarbo.mymovies.domain.model

data class Movie(
    val posterPath: String,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String,
    val title: String,
    val backdropPath: String,
    val popularity: Int,
    val voteCount: Int,
    val voteAverage: Int
)