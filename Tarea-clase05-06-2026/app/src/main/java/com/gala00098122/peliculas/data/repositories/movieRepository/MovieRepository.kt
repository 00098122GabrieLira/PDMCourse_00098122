package com.gala00098122.peliculas.data.repositories.movieRepository

import com.gala00098122.peliculas.model.Movie

interface MovieRepository {
  suspend fun getMovies(): List<Movie>
  suspend fun getMovieById(id: Int): Movie?
  suspend fun getUpcomingMovies(): List<Movie>
}