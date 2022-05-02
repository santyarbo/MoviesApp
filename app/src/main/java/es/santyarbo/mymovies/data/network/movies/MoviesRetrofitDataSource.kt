package es.santyarbo.mymovies.data.network.movies

import arrow.core.Either
import es.santyarbo.mymovies.data.source.MoviesRemoteDataSource
import es.santyarbo.mymovies.di.ApiKey
import es.santyarbo.mymovies.domain.model.Error
import es.santyarbo.mymovies.domain.model.Movie
import es.santyarbo.mymovies.domain.model.tryCall
import javax.inject.Inject

class MoviesRetrofitDataSource @Inject constructor(
    @ApiKey private val apiKey: String,
    private val moviesService: MoviesService
) : MoviesRemoteDataSource {

    override suspend fun getMovies(): Either<Error, List<Movie>> = tryCall {
        moviesService
            .getMovies(apiKey, "es", 1)
            .results
            .toDomain()
    }

    override suspend fun getSimilarTvShows(idMovie: Int): Either<Error, List<Movie>> = tryCall {
        moviesService
            .getSimilarTvShows(idMovie, apiKey, "es", 1)
            .results
            .toDomain()
    }
}


private fun List<MovieDTO>.toDomain(): List<Movie> = map { it.toDomain() }

fun MovieDTO.toDomain() = Movie(
    posterPath = posterPath,
    overview = overview,
    firstAirDate = firstAirDate,
    title = name,
    backdropPath = backdropPath,
    popularity = popularity,
    voteCount = voteCount,
    voteAverage = voteAverage
)