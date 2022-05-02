package es.santyarbo.mymovies.domain.usescases

import arrow.core.Either
import es.santyarbo.mymovies.data.repository.MoviesRepository
import es.santyarbo.mymovies.domain.model.Error
import es.santyarbo.mymovies.domain.model.Movie
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
){
    suspend operator fun invoke(): Either<Error, List<Movie>> = moviesRepository.getMovies()
}