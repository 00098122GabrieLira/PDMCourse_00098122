package com.gala00098122.tarea_room.screens.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gala00098122.tarea_room.scaffold.AppScaffold

@Composable
fun Menu(
  navigateToQuestions: () -> Unit
) {
  
  val context = LocalContext.current
  
  AppScaffold(
    title = "Menu",
    
    ) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(padding)
        .padding(10.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(
        10.dp,
        alignment = Alignment.CenterVertically
      )
    ) {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .height(50.dp)
          .clickable(onClick = { navigateToQuestions() }),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
      ) {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = "Gestor de preguntas"
          )
        }
      }
      
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .height(50.dp)
          .clickable(onClick = {
            Toast.makeText(
              context,
              "Proximamente",
              Toast.LENGTH_SHORT
            ).show()
          }),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
      ) {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = "Votaciones",
          )
        }
      }
    }
  }
}