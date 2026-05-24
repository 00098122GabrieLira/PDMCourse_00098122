package com.gala00098122.peliculas.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {
  @Serializable
  data object Home : Routes()
  
  
  @Serializable
  data class Versions(val movieId: Int) : Routes()
  @Serializable
  data class MovieDetail1(val movieId: Int) : Routes()
  
  
  @Serializable
  data class MovieDetail2(val movieId: Int) : Routes()
  
  @Serializable
  data object Soon : Routes()
}
