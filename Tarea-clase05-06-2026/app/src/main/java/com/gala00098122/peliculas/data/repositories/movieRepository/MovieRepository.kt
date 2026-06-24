package com.gala00098122.peliculas.data.repositories.movieRepository

import com.gala00098122.peliculas.data.model.Movie

interface MovieRepository {
  suspend fun getMovies(): Result<List<Movie>>
  suspend fun getMovieById(id: Int): Result<Movie?>
  suspend fun getUpcomingMovies(): Result<List<Movie>>
}