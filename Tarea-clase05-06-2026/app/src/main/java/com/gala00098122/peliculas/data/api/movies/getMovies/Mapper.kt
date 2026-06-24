package com.gala00098122.peliculas.data.api.movies.getMovies

import com.gala00098122.peliculas.data.model.Movie
private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

fun MovieDto.toModel(): Movie {
  return Movie(
    id = id,
    title = title,
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    overview = overview,
    releaseDate = releaseDate,
    adult = adult,
    genreIds = emptyList(),
    popularity = popularity,
    voteAverage = voteAverage,
    voteCount = voteCount,
    softcore = softcore,
    video = video,
    backdropUrl = backdropPath?.let { "$IMAGE_BASE_URL$it" } ?: "",
    posterUrl = posterPath?.let { "$IMAGE_BASE_URL$it" } ?: ""
  )
}