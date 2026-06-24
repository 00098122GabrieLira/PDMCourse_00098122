package com.gala00098122.peliculas.ui.screens.favoriteMovieScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.gala00098122.peliculas.PeliculasApplication
import com.gala00098122.peliculas.data.model.Movie
import com.gala00098122.peliculas.data.repositories.favoriteMovieRepository.FavoriteMovieRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoriteMovieViewModel(private val favoriteMovieRepository: FavoriteMovieRepository) :
  ViewModel() {
  
  val favoriteMovies: StateFlow<List<Movie>> = favoriteMovieRepository.getFavoriteMovies()
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = emptyList()
    )
  
  val favoriteMoviesIds: StateFlow<Set<Int>> = favoriteMovies
    .map { movies -> movies.map { it.id }.toSet() }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = emptySet()
    )
  
  fun addFavoriteMovie(favoriteMovie: Movie) {
    viewModelScope.launch {
      favoriteMovieRepository.addFavoriteMovie(favoriteMovie)
    }
  }
  
  fun deleteFavoriteMovie(favoriteMovie: Movie) {
    viewModelScope.launch {
      favoriteMovieRepository.deleteFavoriteMovie(favoriteMovie)
    }
  }
  
  fun clearFavoriteMovies() {
    viewModelScope.launch {
      favoriteMovieRepository.clearFavoriteMovies()
    }
  }
  
  companion object {
    fun provideFactory() = viewModelFactory {
      initializer {
        val app = this[APPLICATION_KEY] as PeliculasApplication
        FavoriteMovieViewModel(app.appProvider.provideFavoriteMovieRepository())
      }
    }
  }
  
}