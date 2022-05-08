package es.santyarbo.mymovies.data.network.movies

import androidx.paging.PagingSource
import androidx.paging.PagingState
import es.santyarbo.mymovies.BuildConfig
import es.santyarbo.mymovies.di.ApiKey
import es.santyarbo.mymovies.domain.model.Movie
import retrofit2.HttpException
import java.io.IOException


private const val TMDB_STARTING_PAGE_INDEX = 1

class MoviesPagingSource(
    @ApiKey private val apiKey: String,
    private val service: MoviesService
) : PagingSource<Int, Movie>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
        return try {
            val response = service.getMovies(
                apiKey = apiKey,
                language = "en-US",
                page = pageIndex
            )
            val movies = response.results.map { it.toDomain() }
            val nextKey =
                if (movies.isEmpty()) {
                    null
                } else {
                    pageIndex + (params.loadSize / BuildConfig.NETWORK_PAGE_SIZE)
                }
            LoadResult.Page(
                data = movies,
                prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}