package es.santyarbo.mymovies.domain.usescases

import arrow.core.Either
import es.santyarbo.mymovies.data.repository.MoviesRepository
import es.santyarbo.mymovies.domain.model.Error
import es.santyarbo.mymovies.domain.model.Movie
import javax.inject.Inject

class GetSimilarTvShowsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(idMovie: Int): Either<Error, List<Movie>> = moviesRepository.getSimilarTvShows(idMovie)
}