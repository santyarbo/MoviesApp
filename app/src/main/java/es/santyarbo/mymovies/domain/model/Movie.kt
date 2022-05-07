package es.santyarbo.mymovies.domain.model

data class Movie(
    val id: Long,
    val posterPath: String,
    val overview: String,
    val firstAirDate: String,
    val title: String,
    val backdropPath: String?,
    val popularity: Double,
    val voteCount: Int,
    val voteAverage: Double
)