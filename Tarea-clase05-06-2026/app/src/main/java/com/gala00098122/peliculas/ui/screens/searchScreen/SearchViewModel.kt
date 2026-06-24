package com.gala00098122.peliculas.ui.screens.searchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gala00098122.peliculas.data.repositories.movieRepository.MovieApiRepository
import com.gala00098122.peliculas.data.repositories.movieRepository.MovieRepository
import com.gala00098122.peliculas.data.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
  
  private val movieRepository: MovieRepository = MovieApiRepository()
  
  private val _movies = MutableStateFlow<List<Movie>>(emptyList())
  val movies = _movies.asStateFlow()
  
  private val _searchQuery = MutableStateFlow("")
  val searchQuery = _searchQuery.asStateFlow()
  
  private val _searchResults = MutableStateFlow<List<Movie>>(emptyList())
  val searchResults = _searchResults.asStateFlow()
  private val _loading = MutableStateFlow<Boolean>(false)
  val loading = _loading.asStateFlow()
  
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
          _searchResults.value = emptyList()
        }
        .onFailure { error ->
          _error.value =
            "Ocurrió un error al cargar las películas. Por favor, intenta recargar la página."
          _searchResults.value = emptyList()
        }
      _loading.value = false
    }
  }
  
  fun updateSearchQuery(query: String) {
    _searchQuery.value = query
    performSearch(query)
  }
  
  private fun performSearch(query: String) {
    if (query.isBlank()) {
      _searchResults.value = emptyList()
      return
    }
    
    val lowerCaseQuery = query.lowercase()
    
    val filtered = _movies.value.filter { movie ->
      
      val matchesMovieTitle = movie.title.lowercase().contains(lowerCaseQuery)
      
      val matchesMovieOverview = movie.overview.lowercase().contains(lowerCaseQuery)
      
      matchesMovieTitle || matchesMovieOverview
    }
    
    _searchResults.value = filtered
  }
  
}
