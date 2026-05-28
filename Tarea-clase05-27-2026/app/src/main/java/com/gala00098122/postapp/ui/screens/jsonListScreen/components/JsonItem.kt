package com.gala00098122.postapp.ui.screens.jsonListScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gala00098122.postapp.model.Json

@Composable
fun JsonItem(
  json: Json
) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    shape = RoundedCornerShape(12.dp)
  ) {
    Column(
      modifier = Modifier.fillMaxWidth().padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(
        10.dp,
        alignment = Alignment.CenterVertically
      )
    ) {
      Text(
        text = "UserId: ${json.userId}",
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
      Text(
        text = "Id: ${json.id}",
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
      Text(
        text = "Title: ${json.title}",
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
      Text(
        text = "Body: ${json.body}",
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
    }
  }
}