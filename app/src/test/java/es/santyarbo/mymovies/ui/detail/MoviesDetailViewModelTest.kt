package es.santyarbo.mymovies.ui.detail

import app.cash.turbine.test
import arrow.core.left
import arrow.core.right
import es.santyarbo.mymovies.domain.model.Error
import es.santyarbo.mymovies.domain.usescases.GetSimilarTvShowsUseCase
import es.santyarbo.mymovies.test.buildMovie
import es.santyarbo.mymovies.test.buildMoviesList
import es.santyarbo.mymovies.testrules.CoroutinesTestRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviesDetailViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var getSimilarTvShowsUseCase: GetSimilarTvShowsUseCase

    private lateinit var viewModel: MovieDetailViewModel

    private val movie = buildMovie(1)

    private val similarMovies = buildMoviesList()

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel(movie, getSimilarTvShowsUseCase)
    }

    @Test
    fun `UI is updated with the movie on start`() = runTest {
        whenever(getSimilarTvShowsUseCase(1)).thenReturn(similarMovies.right())
        viewModel.startUI()

        viewModel.state.test {
            assertEquals(MovieDetailViewModel.UiState(loading = true, movie = movie, movies = emptyList()), awaitItem())
            cancel()
        }
    }

    @Test
    fun `UI is updated with the similar movies load success`() = runTest {
        whenever(getSimilarTvShowsUseCase(1)).thenReturn(similarMovies.right())
        viewModel.loadSimilarMovies()

        viewModel.state.test {
            assertEquals(MovieDetailViewModel.UiState(loading = true, movie = movie, movies = emptyList()), awaitItem())
            assertEquals(MovieDetailViewModel.UiState(loading = false, movie = movie, movies = similarMovies), awaitItem())
            cancel()
        }
    }

    @Test
    fun `UI is updated with the similar movies return connectivity error`() = runTest {
        whenever(getSimilarTvShowsUseCase(1)).thenReturn(Error.Connectivity.left())
        viewModel.loadSimilarMovies()

        viewModel.state.test {
            assertEquals(MovieDetailViewModel.UiState(loading = true, movie = movie, movies = emptyList()), awaitItem())
            assertEquals(MovieDetailViewModel.UiState(loading = false, movie = movie, movies = emptyList()), awaitItem())
            cancel()
        }
    }

}