package es.santyarbo.mymovies.domain.usescases

import es.santyarbo.mymovies.data.repository.MoviesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class GetSimilarTvShowsUseCaseTest {

    @Test
    fun `Invoke calls similar tv shows repository`(): Unit = runBlocking {
        val idMovie = 1
        val moviesRepository = mock<MoviesRepository>()
        val getSimilarTvShowsUseCase = GetSimilarTvShowsUseCase(moviesRepository)

        getSimilarTvShowsUseCase(idMovie)

        verify(moviesRepository).getSimilarTvShows(idMovie)
    }
}