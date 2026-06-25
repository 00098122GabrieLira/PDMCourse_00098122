package com.gala00098122.peliculas.ui.screens.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.gala00098122.peliculas.PeliculasApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.gala00098122.peliculas.data.model.Movie
import com.gala00098122.peliculas.data.repositories.offlineMovies.popularMoviesRepository.PopularMovieOfflineRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MovieListViewModel(private val popularMovieRepository: PopularMovieOfflineRepository) :
  ViewModel() {
  
  val movies: StateFlow<List<Movie>> = popularMovieRepository.getPopularMovies()
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = emptyList()
    )
  
  private val _isRefreshing = MutableStateFlow(false)
  val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()
  
  private val _error = MutableStateFlow<String?>(null)
  val error: StateFlow<String?> = _error.asStateFlow()
  
  init {
    refresh()
  }
  
  fun refresh() {
    viewModelScope.launch {
      _error.value = null
      _isRefreshing.value = true
      
      try {
        popularMovieRepository.refreshPopular()
      } catch (_: Exception) {
        if (movies.value.isEmpty()) {
          _error.value = "Sin conexión y sin datos en caché"
        }
      }
      _isRefreshing.value = false
    }
  }
  
  companion object {
    fun provideFactory() = viewModelFactory {
      initializer {
        val app = this[APPLICATION_KEY] as PeliculasApplication
        MovieListViewModel(app.appProvider.providePopularMovieRepository())
      }
    }
  }
}