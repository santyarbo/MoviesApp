package es.santyarbo.mymovies.data.network.movies

import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("/tv/top_rated")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ) : TopRatedResponseDTO
}