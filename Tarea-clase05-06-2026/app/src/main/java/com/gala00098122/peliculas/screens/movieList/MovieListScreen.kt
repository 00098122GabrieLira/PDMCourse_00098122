package com.gala00098122.peliculas.screens.movieList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
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
fun MovieListScreen(
  navigateToVersions: (Int) -> Unit,
  navigateToUpComing: () -> Unit,
  viewModel: MovieListViewModel = viewModel()
) {
  
  val movies by viewModel.movies.collectAsState()
  val loading by viewModel.loading.collectAsState()
  
  LaunchedEffect(Unit) {
    viewModel.loadMovies()
  }
  
  if (loading) {
    AppScaffold(title = "Movies") { padding ->
      CircularProgressIndicator(modifier = Modifier.padding(padding))
    }
    return
  }
  
  AppScaffold(
    title = "Movies",
    actions = {
      Button(
        onClick = { navigateToUpComing() },
        colors = ButtonDefaults.buttonColors(
          containerColor = Color(0xFF3A3737),
          contentColor = Color.White
        )
      ) {
        Text(text = "Proximamente")
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
