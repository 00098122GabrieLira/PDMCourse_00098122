package com.gala00098122.peliculas.data.api.movies.getMovies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMoviesResponseDto(
  val page: Int,
  val results: List<MovieDto>,
  @SerialName("total_pages") val totalPages: Int,
  @SerialName("total_results") val totalResults: Int
)