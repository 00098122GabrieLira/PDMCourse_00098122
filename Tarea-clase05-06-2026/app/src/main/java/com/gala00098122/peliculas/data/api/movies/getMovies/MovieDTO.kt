package com.gala00098122.peliculas.data.api.movies.getMovies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
  val id: Int,
  val title: String,
  @SerialName("original_title") val originalTitle: String,
  @SerialName("original_language") val originalLanguage: String,
  val overview: String,
  @SerialName("release_date") val releaseDate: String,
  val adult: Boolean,
  val popularity: Double,
  @SerialName("vote_average") val voteAverage: Double,
  @SerialName("vote_count") val voteCount: Int,
  val softcore: Boolean,
  val video: Boolean,
  @SerialName("backdrop_path") val backdropPath: String?,
  @SerialName("poster_path") val posterPath: String?
)