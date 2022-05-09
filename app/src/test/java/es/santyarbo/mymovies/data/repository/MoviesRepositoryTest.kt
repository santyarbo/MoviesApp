package es.santyarbo.mymovies.data.repository

import arrow.core.left
import arrow.core.right
import es.santyarbo.mymovies.data.source.MoviesRemoteDataSource
import es.santyarbo.mymovies.domain.model.Error
import es.santyarbo.mymovies.test.buildMoviesList
import es.santyarbo.mymovies.test.buildMoviesPagingData
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MoviesRepositoryTest {

    @Mock
    lateinit var remoteDataSource: MoviesRemoteDataSource

    private lateinit var moviesRepository: MoviesRepository

    private val remoteMovies = flowOf(buildMoviesPagingData())

    private val remoteSimilarMovies = buildMoviesList()

    @Before
    fun setUp() {
        moviesRepository = MoviesRepository(remoteDataSource)
    }

    @Test
    fun `request movies from remote when response success`() = runBlocking {
        whenever(remoteDataSource.getMovies()).thenReturn(remoteMovies)

        val result = moviesRepository.getMovies()

        assertEquals(remoteMovies, result)
    }

    @Test
    fun `request similar movies from remote when response success`() = runBlocking {
        whenever(remoteDataSource.getSimilarTvShows(any())).thenReturn(buildMoviesList().right())

        val result = moviesRepository.getSimilarTvShows(1)

        assertEquals(result, remoteSimilarMovies.right())
    }

    @Test
    fun `request similar movies from remote when error connectivity`() = runBlocking {
        val error = Error.Connectivity
        whenever(remoteDataSource.getSimilarTvShows(any())).thenReturn(error.left())

        val result = moviesRepository.getSimilarTvShows(1)

        assertEquals(result, error.left())
    }

    @Test
    fun `request similar movies from remote when server error`() = runBlocking {
        val error = Error.Server(500)
        whenever(remoteDataSource.getSimilarTvShows(any())).thenReturn(error.left())

        val result = moviesRepository.getSimilarTvShows(1)

        assertEquals(result, error.left())
    }

    @Test
    fun `request similar movies from remote when unknown error`() = runBlocking {
        val error = Error.Unknown("Error")
        whenever(remoteDataSource.getSimilarTvShows(any())).thenReturn(error.left())

        val result = moviesRepository.getSimilarTvShows(1)

        assertEquals(result, error.left())
    }
}