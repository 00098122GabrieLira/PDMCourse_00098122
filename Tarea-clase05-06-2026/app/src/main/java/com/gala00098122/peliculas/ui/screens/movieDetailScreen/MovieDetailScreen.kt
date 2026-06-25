package com.gala00098122.peliculas.ui.screens.movieDetailScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.gala00098122.peliculas.scaffold.AppScaffold
import com.gala00098122.peliculas.ui.screens.favoriteMovieScreen.FavoriteMovieViewModel

@Composable
fun MovieDetailScreen(
  movieId: Int,
  navigateBack: () -> Unit,
  movieDetailViewModel: MovieDetailViewModel = viewModel(
    key = movieId.toString(),
    factory = MovieDetailViewModel.provideFactory(movieId)
  ),
  favoriteMovieViewModel: FavoriteMovieViewModel = viewModel(factory = FavoriteMovieViewModel.provideFactory())
) {
  val movie by movieDetailViewModel.movie.collectAsState()
  val favoriteMoviesIds by favoriteMovieViewModel.favoriteMoviesIds.collectAsStateWithLifecycle()
  
  val isFavorite = movie?.id?.let { it in favoriteMoviesIds } ?: false
  
  AppScaffold(
    title = movie?.title ?: "Detalle",
    navigationIcon = {
      IconButton(onClick = navigateBack) {
        Icon(
          imageVector = Icons.AutoMirrored.Filled.ArrowBack,
          contentDescription = "Back"
        )
      }
    }
  ) { padding ->
    when {
      movie == null -> {
        Box(
          modifier = Modifier
            .fillMaxSize()
            .padding(padding),
          contentAlignment = Alignment.Center
        ) {
          CircularProgressIndicator()
        }
      }
      
      movie != null -> {
        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .verticalScroll(rememberScrollState())
        ) {
          AsyncImage(
            model = movie!!.backdropUrl,
            contentDescription = movie!!.title,
            modifier = Modifier
              .fillMaxWidth()
              .height(220.dp),
            contentScale = ContentScale.Crop
          )
          Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Text(
              text = movie!!.title,
              style = MaterialTheme.typography.headlineSmall,
              fontWeight = FontWeight.Bold
            )
            
            Text(
              text = movie!!.originalTitle,
              style = MaterialTheme.typography.bodyMedium,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Text(
              text = "Rating: ${"%.2f".format(movie!!.voteAverage)}  -  ${movie!!.releaseDate}",
              style = MaterialTheme.typography.bodyMedium
            )
            
            Text(
              text = "Popularidad: ${"%.1f".format(movie!!.popularity)}",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Text(
              text = "Sinopsis",
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.Bold
            )
            
            Text(
              text = movie!!.overview,
              style = MaterialTheme.typography.bodyMedium
            )
            
            if (isFavorite) {
              Text(text = "❤️ En tus favoritas")
            } else {
              Text(text = "")
            }
          }
        }
      }
    }
  }
}