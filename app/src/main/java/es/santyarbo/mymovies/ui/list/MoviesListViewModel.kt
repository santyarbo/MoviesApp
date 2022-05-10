package es.santyarbo.mymovies.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import es.santyarbo.mymovies.domain.model.Error
import es.santyarbo.mymovies.domain.model.Movie
import es.santyarbo.mymovies.domain.usescases.GetMoviesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        _state.update { it.copy(loading = true) }
    }

    suspend fun getMovies() : Flow<PagingData<Movie>> {
        val movies = getMoviesUseCase.invoke()
        _state.update { it.copy(movies = movies.first(), loading = false) }
        return movies.cachedIn(viewModelScope)
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: PagingData<Movie>? = null,
        val error: Error? = null
    )
}