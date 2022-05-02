package es.santyarbo.mymovies.data.network.movies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponseDTO(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<MovieDTO>,
    @SerialName("total_results")
    val totalResults: Int,
    @SerialName("total_pages")
    val totalPages: Int
)