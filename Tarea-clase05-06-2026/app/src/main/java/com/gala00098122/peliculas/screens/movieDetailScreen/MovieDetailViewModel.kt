package com.gala00098122.peliculas.screens.movieDetailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.gala00098122.peliculas.model.Movie
import com.gala00098122.peliculas.dummy.dummyMovies


class MovieDetailViewModel : ViewModel() {
  
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