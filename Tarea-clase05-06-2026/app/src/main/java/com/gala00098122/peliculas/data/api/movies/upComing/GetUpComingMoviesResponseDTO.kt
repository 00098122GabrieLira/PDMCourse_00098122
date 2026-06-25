package com.gala00098122.peliculas.data.api.movies.upComing

import com.gala00098122.peliculas.data.api.movies.getMovies.MovieDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetUpcomingMoviesResponseDTO(
  val dates: DateRangeDTO,
  val page: Int,
  val results: List<MovieDTO>,
  @SerialName("total_pages") val totalPages: Int,
  @SerialName("total_results") val totalResults: Int
)

@Serializable
data class DateRangeDTO(
  val maximum: String,
  val minimum: String
)