package com.gala00098122.peliculas.ui.screens.movieDetailScreenV2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.gala00098122.peliculas.PeliculasApplication
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.gala00098122.peliculas.data.model.Movie
import com.gala00098122.peliculas.data.repositories.offlineMovies.popularMoviesRepository.PopularMovieOfflineRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class MovieDetailViewModelV2(
  private val popularMovieRepository: PopularMovieOfflineRepository,
  private val movieId: Int
) : ViewModel() {
  
  val movie: StateFlow<Movie?> = popularMovieRepository.getPopularMovieById(movieId)
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = null
    )
  
  init {
    refresh()
  }
  
  fun refresh() {
    viewModelScope.launch {
      try {
        popularMovieRepository.refreshPopularMovieById(movieId)
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }
  
  companion object {
    fun provideFactory(movieId: Int) = viewModelFactory {
      initializer {
        val app = this[APPLICATION_KEY] as PeliculasApplication
        MovieDetailViewModelV2(
          popularMovieRepository = app.appProvider.providePopularMovieRepository(),
          movieId = movieId
        )
      }
    }
  }
  
}