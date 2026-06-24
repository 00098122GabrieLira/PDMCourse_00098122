package com.gala00098122.peliculas.data

import android.content.Context
import com.gala00098122.peliculas.data.database.AppDatabase
import com.gala00098122.peliculas.data.repositories.favoriteMovieRepository.FavoriteMovieRepository
import com.gala00098122.peliculas.data.repositories.favoriteMovieRepository.FavoriteMovieRepositoryImpl

class AppProvider(context: Context) {
  private val appDatabase = AppDatabase.getDatabase(context)
  
  private val favoriteMovieDAO = appDatabase.favoriteMovieDAO()
  
  private val favoriteMovieRepository: FavoriteMovieRepository =
    FavoriteMovieRepositoryImpl(favoriteMovieDAO)
  
  fun provideFavoriteMovieRepository(): FavoriteMovieRepository {
    return favoriteMovieRepository
  }
}