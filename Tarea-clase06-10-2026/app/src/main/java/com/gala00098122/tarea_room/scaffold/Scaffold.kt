package com.gala00098122.tarea_room.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
  modifier: Modifier = Modifier,
  title: String = "",
  navigationIcon: @Composable (() -> Unit)? = null,
  actions: @Composable (() -> Unit)? = null,
  bottomBarText: String? = null,
  onFabClick: (() -> Unit)? = null,
  fabIcon: @Composable (() -> Unit)? = null,
  snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
  content: @Composable (PaddingValues) -> Unit
) {
  Scaffold(
    modifier = modifier.fillMaxSize(),
    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    containerColor = Color(0xFFFFFFFF),
    topBar = {
      if (title.isNotEmpty()) {
        TopAppBar(
          colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF000000),
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White,
          ),
          title = { Text(text= title, color = Color.White) },
          navigationIcon = { navigationIcon?.invoke() },
          actions = { actions?.invoke() }
        )
      }
    },
    bottomBar = {
      if (bottomBarText != null) {
        BottomAppBar(
          containerColor = Color(0xFFFFFFFF),
          contentColor = Color.White,
        ) {
          Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = bottomBarText,
            color = Color.White
          )
        }
      }
    },
    floatingActionButton = {
      if (onFabClick != null) {
        FloatingActionButton(onClick = onFabClick) {
          if (fabIcon != null) fabIcon()
          else Icon(Icons.Default.Add, contentDescription = "Add")
        }
      }
    }
  ) { innerPadding ->
    content(innerPadding)
  }
}