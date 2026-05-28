package com.gala00098122.postapp.ui.screens.jsonListScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gala00098122.postapp.scafold.AppScaffold
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material3.IconButton
import com.gala00098122.postapp.ui.screens.jsonListScreen.components.JsonItem

@Composable
fun JsonListScreen(
  navigateToCreate: () -> Unit,
  viewModel: JsonListViewModel = viewModel()
) {
  val json by viewModel.json.collectAsState()
  val loading by viewModel.loading.collectAsState()
  val error by viewModel.error.collectAsState()
  val refresh by viewModel.refresh.collectAsState()
  
  if (loading) {
    AppScaffold(title = "Cargando...") { padding ->
      CircularProgressIndicator(modifier = Modifier.padding(padding))
    }
    return
  }
  
  if (error != null) {
    AppScaffold(title = "Datos") { padding ->
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
          onClick = { viewModel.loadJson() },
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
    title = "Datos",
    actions = {
      IconButton(onClick = { navigateToCreate() }) {
        Icon(
          Icons.Default.AddCircleOutline,
          contentDescription = "Agregar",
          tint = MaterialTheme.colorScheme.background
        )
      }
    }
  ) { padding ->
    PullToRefreshBox(
      isRefreshing = refresh,
      onRefresh = { viewModel.refreshJson() },
      modifier = Modifier.fillMaxSize()
    ) {
      LazyColumn(
        modifier = Modifier
          .fillMaxSize()
          .padding(padding)
          .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        items(json) { json ->
          JsonItem(
            json = json
          )
        }
      }
    }
  }
}