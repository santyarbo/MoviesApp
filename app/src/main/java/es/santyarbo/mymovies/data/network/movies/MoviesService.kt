package es.santyarbo.mymovies.data.network.movies

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("/tv/top_rated")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ) : MoviesResponseDTO

    @GET("/tv/{tv_id}/similar")
    suspend fun getSimilarTvShows(
        @Path("tv_id") idTv: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ) : MoviesResponseDTO
}