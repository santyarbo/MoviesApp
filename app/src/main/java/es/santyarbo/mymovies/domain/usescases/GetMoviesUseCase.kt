package es.santyarbo.mymovies.domain.usescases

import androidx.paging.PagingData
import arrow.core.Either
import es.santyarbo.mymovies.data.repository.MoviesRepository
import es.santyarbo.mymovies.domain.model.Error
import es.santyarbo.mymovies.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
){
    suspend operator fun invoke(): Flow<PagingData<Movie>> = moviesRepository.getMovies()
}