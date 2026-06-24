package com.gala00098122.peliculas.data.repositories.favoriteMovieRepository

import com.gala00098122.peliculas.data.database.dao.MovieDAO
import com.gala00098122.peliculas.data.database.entities.toEntity
import com.gala00098122.peliculas.data.database.entities.toModel
import com.gala00098122.peliculas.data.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteMovieRepositoryImpl(private val favoriteMovieDAO: MovieDAO) :
  FavoriteMovieRepository {
  
  override fun getFavoriteMovies(): Flow<List<Movie>> {
    return favoriteMovieDAO.getFavoriteMovies().map { list ->
      list.map { it.toModel() }
    }
  }
  
  override suspend fun addFavoriteMovie(favoriteMovie: Movie) {
    return favoriteMovieDAO.insertMovie(favoriteMovie.toEntity())
  }
  
  override suspend fun deleteFavoriteMovie(favoriteMovie: Movie) {
    return favoriteMovieDAO.deleteMovie(favoriteMovie.toEntity())
  }
  
  override fun isFavorite(movieId: Int): Flow<Boolean> {
    return favoriteMovieDAO.isFavorite(movieId)
  }
}