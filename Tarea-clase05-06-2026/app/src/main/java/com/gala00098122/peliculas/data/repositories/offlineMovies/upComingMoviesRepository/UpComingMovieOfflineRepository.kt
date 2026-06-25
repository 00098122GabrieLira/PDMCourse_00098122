package com.gala00098122.peliculas.data.repositories.offlineMovies.upComingMoviesRepository

import com.gala00098122.peliculas.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface UpComingMovieOfflineRepository {
  fun getUpComingMovies(): Flow<List<Movie>>
  fun getUpComingMovieById(id: Int): Flow<Movie?>
  
  // Sincronizar: van a la API y guardan en Room
  suspend fun refreshUpComing()
  suspend fun refreshUpComingMovieById(id: Int)
}