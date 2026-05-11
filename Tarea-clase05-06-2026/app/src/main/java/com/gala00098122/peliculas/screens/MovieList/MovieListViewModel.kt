package com.gala00098122.peliculas.screens.MovieList

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.gala00098122.peliculas.model.Movie
import com.gala00098122.peliculas.dummy.dummyMovies

class MovieListViewModel : ViewModel() {
  private val _movies = MutableStateFlow<List<Movie>>(emptyList())
  val movies = _movies.asStateFlow()
  
  private val _loading = MutableStateFlow<Boolean>(false)
  val loading = _loading.asStateFlow()
  
  fun loadMovies() {
    _loading.value = true
    // Simulate loading movies from an API or database
    _movies.value = dummyMovies
    _loading.value = false
  }
}