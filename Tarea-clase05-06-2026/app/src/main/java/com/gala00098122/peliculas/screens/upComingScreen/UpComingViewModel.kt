package com.gala00098122.peliculas.screens.upComingScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gala00098122.peliculas.data.repositories.movieRepository.MovieApiRepository
import com.gala00098122.peliculas.data.repositories.movieRepository.MovieRepository
import com.gala00098122.peliculas.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UpComingViewModel: ViewModel() {
  private val movieRepository: MovieRepository = MovieApiRepository()
  
  private val _movies = MutableStateFlow<List<Movie>>(emptyList())
  val movies = _movies.asStateFlow()
  
  private val _loading = MutableStateFlow<Boolean>(false)
  val loading = _loading.asStateFlow()
  
  fun loadMovies() {
    viewModelScope.launch {
      _loading.value = true
      _movies.value = movieRepository.getUpcomingMovies()
      _loading.value = false
    }
  }
}