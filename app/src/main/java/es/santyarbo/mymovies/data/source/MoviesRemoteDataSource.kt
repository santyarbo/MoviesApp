package es.santyarbo.mymovies.data.source

import androidx.paging.PagingData
import arrow.core.Either
import es.santyarbo.mymovies.domain.model.Error
import es.santyarbo.mymovies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRemoteDataSource {
    suspend fun getMovies(): Flow<PagingData<Movie>>
    suspend fun getSimilarTvShows(idMovie: Int): Either<Error, List<Movie>>
}