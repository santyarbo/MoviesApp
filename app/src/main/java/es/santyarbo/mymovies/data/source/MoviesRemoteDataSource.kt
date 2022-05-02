package es.santyarbo.mymovies.data.source

import arrow.core.Either
import es.santyarbo.mymovies.domain.model.Error
import es.santyarbo.mymovies.domain.model.Movie

interface MoviesRemoteDataSource {
    suspend fun getMovies(): Either<Error, List<Movie>>
    suspend fun getSimilarTvShows(idMovie: Int): Either<Error, List<Movie>>
}