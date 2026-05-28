package com.gala00098122.postapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.gala00098122.postapp.navegacion.JsonNavigation
import com.gala00098122.postapp.ui.theme.PostAppTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      PostAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          JsonNavigation(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}