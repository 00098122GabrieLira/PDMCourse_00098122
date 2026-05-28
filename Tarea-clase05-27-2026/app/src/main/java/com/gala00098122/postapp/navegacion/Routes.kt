package com.gala00098122.postapp.navegacion

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {
  @Serializable
  data object Home : Routes()
  
  @Serializable
  data object Create : Routes()
}
