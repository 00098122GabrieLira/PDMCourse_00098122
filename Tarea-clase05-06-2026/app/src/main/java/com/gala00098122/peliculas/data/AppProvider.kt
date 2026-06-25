package com.gala00098122.peliculas.data

import android.content.Context
import com.gala00098122.peliculas.data.database.AppDatabase
import com.gala00098122.peliculas.data.repositories.favoriteMovieRepository.FavoriteMovieRepository
import com.gala00098122.peliculas.data.repositories.favoriteMovieRepository.FavoriteMovieRepositoryImpl
import com.gala00098122.peliculas.data.repositories.offlineMovies.popularMoviesRepository.PopularMovieOfflineRepository
import com.gala00098122.peliculas.data.repositories.offlineMovies.popularMoviesRepository.PopularMovieOfflineRepositoryImpl
import com.gala00098122.peliculas.data.repositories.offlineMovies.upComingMoviesRepository.UpComingMovieOfflineRepository
import com.gala00098122.peliculas.data.repositories.offlineMovies.upComingMoviesRepository.UpComingMovieOfflineRepositoryImpl

class AppProvider(context: Context) {
  private val appDatabase = AppDatabase.getDatabase(context)
  
  private val favoriteMovieDAO = appDatabase.favoriteMovieDAO()
  
  private val popularMovieDAO = appDatabase.popularMovieDAO()
  
  private val upComingMovieDAO = appDatabase.upComingMovieDAO()
  
  private val favoriteMovieRepository: FavoriteMovieRepository =
    FavoriteMovieRepositoryImpl(favoriteMovieDAO)
  
  private val popularMovieRepository: PopularMovieOfflineRepository =
    PopularMovieOfflineRepositoryImpl(popularMovieDAO)
  
  private val upComingMovieRepository: UpComingMovieOfflineRepository =
    UpComingMovieOfflineRepositoryImpl(upComingMovieDAO)
  
  fun provideFavoriteMovieRepository(): FavoriteMovieRepository {
    return favoriteMovieRepository
  }
  
  fun providePopularMovieRepository(): PopularMovieOfflineRepository {
    return popularMovieRepository
  }
  
  fun provideUpComingMovieRepository(): UpComingMovieOfflineRepository{
    return upComingMovieRepository
  }
}