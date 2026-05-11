package com.gala00098122.peliculas.screens.movieDetailScreenV2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.gala00098122.peliculas.dummy.dummyMovies
import com.gala00098122.peliculas.model.Movie

class MovieDetailViewModelV2 : ViewModel() {
  
  private val _movie = MutableStateFlow<Movie?>(null)
  val movie: StateFlow<Movie?> = _movie.asStateFlow()
  
  private val _isLoading = MutableStateFlow(false)
  val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
  
  private val _error = MutableStateFlow<String?>(null)
  val error: StateFlow<String?> = _error.asStateFlow()
  
  fun loadMovie(movieId: Int) {
    viewModelScope.launch {
      _isLoading.value = true
      _error.value = null
      
      // Simular delay de carga
      kotlinx.coroutines.delay(500)
      
      val foundMovie = dummyMovies.find { it.id == movieId }
      if (foundMovie != null) {
        _movie.value = foundMovie
      } else {
        _error.value = "Película no encontrada"
      }
      
      _isLoading.value = false
    }
  }
}