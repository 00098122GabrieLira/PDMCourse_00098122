package com.gala00098122.peliculas.ui.screens.favoriteMovieScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gala00098122.peliculas.components.MovieItem
import com.gala00098122.peliculas.scaffold.AppScaffold
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment

@Composable
fun FavoriteMovieScreen(
  navigateBack: () -> Unit,
  navigateToVersions: (Int) -> Unit,
  viewModel: FavoriteMovieViewModel = viewModel(factory = FavoriteMovieViewModel.provideFactory())
) {
  
  val favoriteMovies by viewModel.favoriteMovies.collectAsStateWithLifecycle()
  
  AppScaffold(
    title = "Favoritas",
    actions = {
      Button(
        onClick = { viewModel.clearFavoriteMovies() },
        colors = ButtonDefaults.buttonColors(
          containerColor = MaterialTheme.colorScheme.primary,
          contentColor = MaterialTheme.colorScheme.onPrimary
        )
      ) {
        Text(text = "Vaciar")
      }
    },
    navigationIcon = {
      IconButton(onClick = { navigateBack() }) {
        Icon(
          imageVector = Icons.AutoMirrored.Filled.ArrowBack,
          contentDescription = "Back"
        )
      }
    }
  ) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(padding)
        .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
      
      if (favoriteMovies.isEmpty()) {
        Column(
          modifier = Modifier.fillMaxSize(),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterVertically)
        ) {
          
          Icon(
            imageVector = Icons.Default.HeartBroken,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier.size(72.dp)
          )
          
          Text(
            text = "Todavia no has escogido tus favoritas",
            style = MaterialTheme.typography.titleMedium
          )
        }
      } else {
        Text(
          text = "${if (favoriteMovies.size == 1) "Tu favorita" else "Tus favoritas"}: ${favoriteMovies.size}",
          modifier = Modifier.padding(vertical = 8.dp)
        )
        LazyColumn(
          modifier = Modifier.fillMaxSize()
        ) {
          items(favoriteMovies) { movie ->
            MovieItem(
              movie = movie,
              isFavorite = true,
              onClick = { navigateToVersions(movie.id) },
              addFavorite = { viewModel.addFavoriteMovie(movie) },
              deleteFavorite = { viewModel.deleteFavoriteMovie(movie) }
            )
            Spacer(modifier = Modifier.height(12.dp))
          }
        }
      }
    }
  }
}
  
