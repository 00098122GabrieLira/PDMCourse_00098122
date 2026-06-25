package com.gala00098122.peliculas.ui.screens.upComingScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.gala00098122.peliculas.PeliculasApplication
import com.gala00098122.peliculas.data.model.Movie
import com.gala00098122.peliculas.data.repositories.offlineMovies.upComingMoviesRepository.UpComingMovieOfflineRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UpComingViewModel(private val upComingMovieRepository: UpComingMovieOfflineRepository) :
  ViewModel() {
  
  val movies: StateFlow<List<Movie>> = upComingMovieRepository.getUpComingMovies()
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
        upComingMovieRepository.refreshUpComing()
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
        UpComingViewModel(app.appProvider.provideUpComingMovieRepository())
      }
    }
  }
}