package es.santyarbo.mymovies.ui.list

import app.cash.turbine.test
import es.santyarbo.mymovies.domain.usescases.GetMoviesUseCase
import es.santyarbo.mymovies.test.buildMoviesPagingData
import es.santyarbo.mymovies.testrules.CoroutinesTestRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviesListViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var getMoviesUseCase: GetMoviesUseCase

    private lateinit var viewModel: MoviesListViewModel

    private val movies = buildMoviesPagingData()

    @Before
    fun setUp() {
        viewModel = MoviesListViewModel(getMoviesUseCase)
    }

    @Test
    fun `Progress is shown when screen starts and hidden when it finishes requesting movies`() = runTest {
        whenever(getMoviesUseCase()).thenReturn(flowOf(movies))
        viewModel.getMovies()

        viewModel.state.test {
            assertEquals(MoviesListViewModel.UiState(loading = false, movies = movies, error = null), awaitItem())
            cancel()
        }
    }

    @Test
    fun `Popular movies are requested when UI screen starts`() = runTest {
        whenever(getMoviesUseCase()).thenReturn(flowOf(movies))
        viewModel.getMovies()
        runCurrent()

        verify(getMoviesUseCase).invoke()
    }

}