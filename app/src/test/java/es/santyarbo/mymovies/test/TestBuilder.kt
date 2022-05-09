package es.santyarbo.mymovies.test

import androidx.paging.PagingData
import es.santyarbo.mymovies.domain.model.Movie

fun buildMoviesPagingData(): PagingData<Movie> {
    return PagingData.from(buildMoviesList())
}

fun buildMoviesList(): List<Movie> {
    val movie1 = Movie(
        id = 1,
        title = "Title",
        overview = "Overview",
        firstAirDate = "01/01/2025",
        posterPath = "",
        backdropPath = "",
        originalLanguage = "EN",
        popularity = 5.0,
        voteAverage = 5.1,
        voteCount = 100
    )
    val movie2 = Movie(
        id = 2,
        title = "Title",
        overview = "Overview",
        firstAirDate = "01/01/2025",
        posterPath = "",
        backdropPath = "",
        originalLanguage = "EN",
        popularity = 5.0,
        voteAverage = 5.1,
        voteCount = 100
    )
    return listOf(movie1, movie2)
}