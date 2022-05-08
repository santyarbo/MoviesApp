package es.santyarbo.mymovies.data.repository

import androidx.paging.PagingData
import arrow.core.Either
import es.santyarbo.mymovies.data.source.MoviesRemoteDataSource
import es.santyarbo.mymovies.domain.model.Error
import es.santyarbo.mymovies.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) {

    suspend fun getMovies(): Flow<PagingData<Movie>> = moviesRemoteDataSource.getMovies()

    suspend fun getSimilarTvShows(idMovie: Int): Either<Error, List<Movie>> = moviesRemoteDataSource.getSimilarTvShows(idMovie)
}