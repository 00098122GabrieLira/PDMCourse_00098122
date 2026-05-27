package com.gala00098122.peliculas.screens.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gala00098122.peliculas.data.repositories.movieRepository.MovieApiRepository
import com.gala00098122.peliculas.data.repositories.movieRepository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.gala00098122.peliculas.model.Movie
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {
  
  private val movieRepository: MovieRepository = MovieApiRepository()
  
  private val _movies = MutableStateFlow<List<Movie>>(emptyList())
  val movies = _movies.asStateFlow()
  
  private val _loading = MutableStateFlow<Boolean>(false)
  val loading = _loading.asStateFlow()
  
  private val _refresh = MutableStateFlow<Boolean>(false)
  val refresh = _refresh.asStateFlow()
  
  private val _error = MutableStateFlow<String?>(null)
  val error = _error.asStateFlow()
  
  init {
    loadMovies()
  }
  
  fun loadMovies() {
    viewModelScope.launch {
      _error.value = null
      _loading.value = true
      
      movieRepository.getMovies()
        .onSuccess { movies ->
          _movies.value = movies
          
        }
        .onFailure { error ->
          _error.value =
            "Ocurrió un error al cargar las películas. Por favor, intenta recargar la página."
        }
      
      _loading.value = false
    }
  }
  
  fun refreshMovies() {
    viewModelScope.launch {
      _error.value = null
      _refresh.value = true
      
      movieRepository.getMovies()
        .onSuccess { movies ->
          _movies.value = movies
          
        }
        .onFailure { error ->
          _error.value =
            "Ocurrió un error al recargar las películas. Por favor, intenta recargar la página."
        }
      
      _refresh.value = false
    }
  }
}