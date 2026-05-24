package com.gala00098122.peliculas.data.api.movies.upComing

import com.gala00098122.peliculas.data.api.movies.getMovies.MovieDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetUpcomingMoviesResponseDto(
  val dates: DateRangeDto,
  val page: Int,
  val results: List<MovieDto>,
  @SerialName("total_pages") val totalPages: Int,
  @SerialName("total_results") val totalResults: Int
)

@Serializable
data class DateRangeDto(
  val maximum: String,
  val minimum: String
)