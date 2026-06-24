package com.gala00098122.peliculas.data.repositories.favoriteMovieRepository

import com.gala00098122.peliculas.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieRepository {
  fun getFavoriteMovies(): Flow<List<Movie>>
  suspend fun addFavoriteMovie(favoriteMovie: Movie)
  suspend fun deleteFavoriteMovie(favoriteMovie: Movie)
  suspend fun clearFavoriteMovies()
}