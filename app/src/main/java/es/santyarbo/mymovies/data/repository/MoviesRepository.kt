package es.santyarbo.mymovies.data.repository

import arrow.core.Either
import es.santyarbo.mymovies.data.source.MoviesRemoteDataSource
import es.santyarbo.mymovies.domain.model.Error
import es.santyarbo.mymovies.domain.model.Movie
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) {

    suspend fun getMovies(): Either<Error, List<Movie>> = moviesRemoteDataSource.getMovies()

    suspend fun getSimilarTvShows(idMovie: Int): Either<Error, List<Movie>> = moviesRemoteDataSource.getSimilarTvShows(idMovie)
}