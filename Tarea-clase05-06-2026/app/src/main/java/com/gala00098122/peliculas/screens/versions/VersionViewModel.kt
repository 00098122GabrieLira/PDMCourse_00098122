package com.gala00098122.peliculas.screens.versions

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.gala00098122.peliculas.model.Movie

class VersionViewModel : ViewModel() {
  
  private val _movie = MutableStateFlow<Movie?>(null)
  val movie: StateFlow<Movie?> = _movie.asStateFlow()
  
}