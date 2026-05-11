package com.gala00098122.peliculas.screens.versions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gala00098122.peliculas.scaffold.AppScaffold

@Composable
fun VersionScreen(
  movieId: Int,
  navigateBack: () -> Unit,
  navigateToDetail1: (Int) -> Unit,
  navigateToDetail2: (Int) -> Unit,
  viewModel: VersionViewModel = viewModel()
) {
  
  val movie by viewModel.movie.collectAsState()
  
  AppScaffold(
    title = movie?.title ?: "Version",
    navigationIcon = {
      IconButton(onClick = navigateBack) {
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
        .background(Color.Black)
        .padding(padding),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(
        20.dp,
        alignment = Alignment.CenterVertically
      )
    ) {
      Button(
        onClick = { navigateToDetail1(movieId) },
        colors = ButtonDefaults.buttonColors(
          containerColor = Color.White,
          contentColor = Color.Black
        )
      ) {
        Text(text = "Version 1")
      }
      
      Button(
        onClick = { navigateToDetail2(movieId) },
        colors = ButtonDefaults.buttonColors(
          containerColor = Color.White,
          contentColor = Color.Black
        )
      ) {
        Text(text = "Version 2")
      }
    }
  }
}