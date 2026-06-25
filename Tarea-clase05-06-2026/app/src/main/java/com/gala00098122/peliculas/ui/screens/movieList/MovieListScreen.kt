package com.gala00098122.peliculas.ui.screens.movieList

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gala00098122.peliculas.components.MovieItem
import com.gala00098122.peliculas.scaffold.AppScaffold
import com.gala00098122.peliculas.ui.screens.favoriteMovieScreen.FavoriteMovieViewModel


@Composable
fun MovieListScreen(
  navigateToVersions: (Int) -> Unit,
  navigateToUpComing: () -> Unit,
  navigateToSearch: () -> Unit,
  navigateToFavorites: () -> Unit,
  movieListViewModel: MovieListViewModel = viewModel(factory = MovieListViewModel .provideFactory()),
  favoriteMovieViewModel: FavoriteMovieViewModel = viewModel(factory = FavoriteMovieViewModel.provideFactory())
) {
  
  val movies by movieListViewModel.movies.collectAsState()
  val isRefreshing by movieListViewModel.isRefreshing.collectAsState()
  val error by movieListViewModel.error.collectAsState()
  val favoriteMoviesIds by favoriteMovieViewModel.favoriteMoviesIds.collectAsStateWithLifecycle()
  
  if (movies.isEmpty() && isRefreshing) {
    AppScaffold(title = "Cargando...") { padding ->
      CircularProgressIndicator(modifier = Modifier.padding(padding))
    }
    return
  }
  
  if (movies.isEmpty() && error != null) {
    AppScaffold(title = "Peliculas") { padding ->
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(padding)
          .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
          12.dp,
          alignment = Alignment.CenterVertically
        )
      ) {
        Icon(
          imageVector = Icons.Default.ErrorOutline,
          contentDescription = "Error",
          tint = MaterialTheme.colorScheme.primary,
          modifier = Modifier.size(72.dp)
        )
        Text(
          text = "$error",
          textAlign = TextAlign.Center,
        )
        Button(
          onClick = { movieListViewModel.refresh() },
          colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
          )
        ) {
          Text(
            text = "Reintentar"
          )
        }
      }
    }
    return
  }
  
  AppScaffold(
    title = "Peliculas",
    actions = {
      Button(
        onClick = { navigateToUpComing() },
        colors = ButtonDefaults.buttonColors(
          containerColor = MaterialTheme.colorScheme.primary,
          contentColor = MaterialTheme.colorScheme.onPrimary
        )
      ) {
        Text(text = "Proximamente")
      }
      IconButton(onClick = { navigateToSearch() }) {
        Icon(
          Icons.Default.Search,
          contentDescription = "Buscar",
        )
      }
      IconButton(onClick = { navigateToFavorites() }) {
        Icon(
          Icons.Default.Favorite,
          contentDescription = "Buscar",
        )
      }
    }
  ) { padding ->
    PullToRefreshBox(
      isRefreshing = isRefreshing,
      onRefresh = { movieListViewModel.refresh() },
      modifier = Modifier.fillMaxSize()
    ) {
      LazyColumn(
        modifier = Modifier
          .fillMaxSize()
          .background(Color.Black)
          .padding(padding)
          .padding(16.dp),
      ) {
        items(movies) { movie ->
          val isFavorite = movie.id in favoriteMoviesIds
          MovieItem(
            movie = movie,
            isFavorite = isFavorite,
            onClick = { navigateToVersions(movie.id) },
            addFavorite = { favoriteMovieViewModel.addFavoriteMovie(movie) },
            deleteFavorite = { favoriteMovieViewModel.deleteFavoriteMovie(movie) }
          )
          Spacer(modifier = Modifier.height(12.dp))
        }
      }
    }
  }
}
