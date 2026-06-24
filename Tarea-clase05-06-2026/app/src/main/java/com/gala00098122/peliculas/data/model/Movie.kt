package com.gala00098122.peliculas.data.model

data class Movie(
  val id: Int,
  val title: String,
  val originalTitle: String,
  val originalLanguage: String,
  val overview: String,
  val releaseDate: String,
  val adult: Boolean,
  val genreIds: List<Int>,
  val popularity: Double,
  val voteAverage: Double,
  val voteCount: Int,
  val softcore: Boolean,
  val video: Boolean,
  val backdropUrl: String,
  val posterUrl: String
)