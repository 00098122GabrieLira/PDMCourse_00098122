package com.gala00098122.peliculas.ui.screens.movieDetailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gala00098122.peliculas.data.repositories.movieRepository.MovieApiRepository
import com.gala00098122.peliculas.data.repositories.movieRepository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.gala00098122.peliculas.data.model.Movie


class MovieDetailViewModel : ViewModel() {
  
  private val movieRepository: MovieRepository = MovieApiRepository()
  
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
      
      movieRepository.getMovieById(movieId)
        .onSuccess { movie ->
          _movie.value = movie
        }
        .onFailure { error ->
          _error.value = "Pelicula no encontrada"
        }
      
      _isLoading.value = false
    }
  }
}