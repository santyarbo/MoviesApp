package es.santyarbo.mymovies.data.source

import es.santyarbo.mymovies.domain.model.Movie

interface MoviesRemoteDataSource {
    suspend fun getMovies() : List<Movie>
}