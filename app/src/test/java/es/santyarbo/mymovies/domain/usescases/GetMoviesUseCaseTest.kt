package es.santyarbo.mymovies.domain.usescases

import es.santyarbo.mymovies.data.repository.MoviesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class GetMoviesUseCaseTest {

    @Test
    fun `Invoke calls movies repository`(): Unit = runBlocking {
        val moviesRepository = mock<MoviesRepository>()
        val getPopularMoviesUseCase = GetMoviesUseCase(moviesRepository)

        getPopularMoviesUseCase()

        verify(moviesRepository).getMovies()
    }
}