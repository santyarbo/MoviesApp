package es.santyarbo.mymovies.data.network.movies

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import arrow.core.Either
import es.santyarbo.mymovies.BuildConfig
import es.santyarbo.mymovies.data.source.MoviesRemoteDataSource
import es.santyarbo.mymovies.di.ApiKey
import es.santyarbo.mymovies.domain.model.Error
import es.santyarbo.mymovies.domain.model.Movie
import es.santyarbo.mymovies.domain.model.tryCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRetrofitDataSource @Inject constructor(
    @ApiKey private val apiKey: String,
    private val moviesService: MoviesService
) : MoviesRemoteDataSource {

    /*override suspend fun getMovies(): Either<Error, List<Movie>> = tryCall {
        moviesService
            .getMovies(apiKey, "es-ES", 1)
            .results
            .toDomain()
    }*/

    override suspend fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = BuildConfig.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviesPagingSource(service = moviesService, apiKey = apiKey)
            }
        ).flow
    }

    override suspend fun getSimilarTvShows(idMovie: Int): Either<Error, List<Movie>> = tryCall {
        moviesService
            .getSimilarTvShows(idMovie, apiKey, "es-ES", 1)
            .results
            .toDomain()
    }
}


private fun List<MovieDTO>.toDomain(): List<Movie> = map { it.toDomain() }

fun MovieDTO.toDomain() = Movie(
    posterPath =  posterPath?.let { "https://image.tmdb.org/t/p/w500/$it" },
    overview = overview,
    firstAirDate = firstAirDate,
    title = name,
    backdropPath = backdropPath,
    popularity = popularity,
    voteCount = voteCount,
    voteAverage = voteAverage,
    id = id,
    originalLanguage = originalLanguage,
)