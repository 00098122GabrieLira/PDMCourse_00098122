package com.gala00098122.peliculas.data.repositories.favoriteMovieRepository

import com.gala00098122.peliculas.data.database.dao.FavoriteMovieDAO
import com.gala00098122.peliculas.data.database.entities.toFavoriteEntity
import com.gala00098122.peliculas.data.database.entities.toModel
import com.gala00098122.peliculas.data.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteMovieRepositoryImpl(private val favoriteMovieDAO: FavoriteMovieDAO) :
  FavoriteMovieRepository {
  
  override fun getFavoriteMovies(): Flow<List<Movie>> {
    return favoriteMovieDAO.getFavoriteMovies().map { list ->
      list.map { it.toModel() }
    }
  }
  
  override suspend fun addFavoriteMovie(favoriteMovie: Movie) {
    return favoriteMovieDAO.insertMovie(favoriteMovie.toFavoriteEntity())
  }
  
  override suspend fun deleteFavoriteMovie(favoriteMovie: Movie) {
    return favoriteMovieDAO.deleteMovie(favoriteMovie.toFavoriteEntity())
  }
  
  override suspend fun clearFavoriteMovies() {
    return favoriteMovieDAO.clearFavoriteMovies()
  }
}