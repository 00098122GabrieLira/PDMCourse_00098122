package com.gala00098122.peliculas.ui.screens.searchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.gala00098122.peliculas.PeliculasApplication
import com.gala00098122.peliculas.data.model.Movie
import com.gala00098122.peliculas.data.repositories.offlineMovies.popularMoviesRepository.PopularMovieOfflineRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SearchViewModel(private val popularMovieRepository: PopularMovieOfflineRepository) :
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
  
  private val _searchQuery = MutableStateFlow("")
  val searchQuery = _searchQuery.asStateFlow()
  
  private val _searchResults = MutableStateFlow<List<Movie>>(emptyList())
  val searchResults = _searchResults.asStateFlow()
  
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
    
    val filtered = movies.value.filter { movie ->
      
      val matchesMovieTitle = movie.title.lowercase().contains(lowerCaseQuery)
      
      val matchesMovieOverview = movie.overview.lowercase().contains(lowerCaseQuery)
      
      matchesMovieTitle || matchesMovieOverview
    }
    
    _searchResults.value = filtered
  }
  
  companion object {
    fun provideFactory() = viewModelFactory {
      initializer {
        val app = this[APPLICATION_KEY] as PeliculasApplication
        SearchViewModel(app.appProvider.providePopularMovieRepository())
      }
    }
  }
  
}
