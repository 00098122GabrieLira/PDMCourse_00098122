package com.gala00098122.peliculas.data.repositories.offlineMovies.popularMoviesRepository

import com.gala00098122.peliculas.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface PopularMovieOfflineRepository {
  fun getPopularMovies(): Flow<List<Movie>>
  fun getPopularMovieById(id: Int): Flow<Movie?>
  
  // Sincronizar: van a la API y guardan en Room
  suspend fun refreshPopular()
  suspend fun refreshPopularMovieById(id: Int)
}