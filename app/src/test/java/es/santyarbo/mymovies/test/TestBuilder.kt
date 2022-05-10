package es.santyarbo.mymovies.test

import androidx.paging.PagingData
import es.santyarbo.mymovies.domain.model.Movie

fun buildMoviesPagingData(): PagingData<Movie> {
    return PagingData.from(buildMoviesList())
}

fun buildMoviesList(): List<Movie> {
    return listOf(buildMovie(1), buildMovie(2))
}

fun buildMovie(id: Long) = Movie(
    id = id,
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