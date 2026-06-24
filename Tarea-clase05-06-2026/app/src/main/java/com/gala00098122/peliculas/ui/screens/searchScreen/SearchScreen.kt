package com.gala00098122.peliculas.ui.screens.searchScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gala00098122.peliculas.scaffold.AppScaffold
import com.gala00098122.peliculas.ui.screens.searchScreen.components.CustomizableSearchBar
import androidx.compose.foundation.lazy.items
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gala00098122.peliculas.components.MovieItem
import com.gala00098122.peliculas.ui.screens.favoriteMovieScreen.FavoriteMovieViewModel

@Composable
fun SearchScreen(
  navigateBack: () -> Unit,
  navigateToVersions: (Int) -> Unit,
  searchViewModel: SearchViewModel = viewModel(),
  favoriteMovieViewModel: FavoriteMovieViewModel = viewModel(factory = FavoriteMovieViewModel.provideFactory())
) {
  
  val searchResults by searchViewModel.searchResults.collectAsState()
  val searchQuery by searchViewModel.searchQuery.collectAsState()
  val loading by searchViewModel.loading.collectAsState()
  val error by searchViewModel.error.collectAsState()
  val favoriteMoviesIds by favoriteMovieViewModel.favoriteMoviesIds.collectAsStateWithLifecycle()
  
  if (loading) {
    AppScaffold(title = "Cargando...") { padding ->
      CircularProgressIndicator(modifier = Modifier.padding(padding))
    }
    return
  }
  
  if (error != null) {
    AppScaffold(title = "Movies") { padding ->
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
          onClick = { searchViewModel.loadMovies() },
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
    title = "Buscar",
    navigationIcon = {
      IconButton(onClick = { navigateBack() }) {
        Icon(
          Icons.AutoMirrored.Filled.ArrowBack,
          contentDescription = "Volver",
        )
      }
    }
  ) { padding ->
    when {
      loading -> {
        CircularProgressIndicator(
          modifier = Modifier.padding(padding)
        )
      }
      
      else -> {
        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
          CustomizableSearchBar(
            query = searchQuery,
            onQueryChange = {
              searchViewModel.updateSearchQuery(it)
            },
            onSearch = {
              searchViewModel.updateSearchQuery(it)
            },
            searchResults = emptyList(),
            onResultClick = {}
          )
          
          when {
            
            searchResults.isNotEmpty() -> {
              Text(
                text = "${searchResults.size} resultado${if (searchResults.size != 1) "s" else ""} encontrado${if (searchResults.size != 1) "s" else ""}",
                modifier = Modifier.padding(vertical = 8.dp)
              )
              
              LazyColumn(
                modifier = Modifier
                  .fillMaxSize()
                  .padding(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
              ) {
                items(searchResults) { movie ->
                  val isFavorite = movie.id in favoriteMoviesIds
                  MovieItem(
                    movie = movie,
                    isFavorite = isFavorite,
                    onClick = { navigateToVersions(movie.id) },
                    addFavorite = { favoriteMovieViewModel.addFavoriteMovie(movie) },
                    deleteFavorite = { favoriteMovieViewModel.deleteFavoriteMovie(movie) }
                  )
                }
              }
            }
            
            searchQuery.isNotBlank() && searchResults.isEmpty() -> {
              Column(
                modifier = Modifier
                  .fillMaxSize()
                  .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
              ) {
                Icon(
                  imageVector = Icons.Default.Search,
                  contentDescription = "Sin resultados",
                  
                  modifier = Modifier.size(72.dp)
                )
                Text(
                  text = "No se encontraron resultados",
                  modifier = Modifier.padding(top = 16.dp)
                )
                
              }
            }
            
            else -> {
              Column(
                modifier = Modifier
                  .fillMaxSize()
                  .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                  5.dp,
                  alignment = Alignment.CenterVertically
                )
              ) {
                Icon(
                  imageVector = Icons.Default.Search,
                  contentDescription = "Buscar",
                  modifier = Modifier.size(72.dp)
                )
                Text(
                  text = "Busca películas o series de tu preferencia",
                  modifier = Modifier.padding(top = 16.dp),
                  textAlign = TextAlign.Center
                )
              }
            }
          }
        }
      }
    }
  }
  
}