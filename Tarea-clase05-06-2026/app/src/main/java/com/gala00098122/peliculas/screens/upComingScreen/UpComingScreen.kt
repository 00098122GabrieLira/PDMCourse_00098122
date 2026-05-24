package com.gala00098122.peliculas.screens.upComingScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gala00098122.peliculas.components.MovieItem
import com.gala00098122.peliculas.scaffold.AppScaffold

@Composable
fun UpComingScreen(
  navigateToVersions: (Int) -> Unit,
  navigateBack: () -> Unit,
  viewModel: UpComingViewModel = viewModel()
) {
  val movies by viewModel.movies.collectAsState()
  val loading by viewModel.loading.collectAsState()
  
  LaunchedEffect(Unit) {
    viewModel.loadMovies()
  }
  
  if (loading) {
    AppScaffold(title = "Cargando...") { padding ->
      CircularProgressIndicator(modifier = Modifier.padding(padding))
    }
    return
  }
  
  AppScaffold(
    title = "Coming Soon",
    navigationIcon = {
      IconButton(onClick = { navigateBack() }) {
        Icon(
          imageVector = Icons.AutoMirrored.Filled.ArrowBack,
          contentDescription = "Back"
        )
      }
    }
  ) { padding ->
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
        .padding(padding)
        .padding(16.dp),
    ) {
      items(movies) { movie ->
        MovieItem(
          movie = movie,
          onClick = { navigateToVersions(movie.id) }
        )
        Spacer(modifier = Modifier.height(12.dp))
      }
    }
  }
}