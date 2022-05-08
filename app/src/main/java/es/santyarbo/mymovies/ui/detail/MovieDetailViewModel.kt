package es.santyarbo.mymovies.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.santyarbo.mymovies.domain.model.Movie
import es.santyarbo.mymovies.domain.usescases.GetSimilarTvShowsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    @es.santyarbo.mymovies.di.Movie private val movie: Movie,
    private val getSimilarTvShowsUseCase: GetSimilarTvShowsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        _state.update { UiState(movie = movie, loading = true) }
        loadSimilarMovies()
    }

    private fun loadSimilarMovies() {
        viewModelScope.launch {
            getSimilarTvShowsUseCase(movie.id.toInt()).fold({ onError() }) { movies ->
                onSuccess(movies)
            }
        }
    }

    private fun onError() {
        _state.update { it.copy(movies = emptyList(), loading = false) }
    }

    private fun onSuccess(movies: List<Movie>) {
        _state.update { it.copy(movies = movies, loading = false) }
    }


    data class UiState(
        val loading: Boolean = false,
        val movie: Movie? = null,
        val movies: List<Movie> = emptyList()
    )
}