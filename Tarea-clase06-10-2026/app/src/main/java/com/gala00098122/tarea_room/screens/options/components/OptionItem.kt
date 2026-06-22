package com.gala00098122.tarea_room.screens.options.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.gala00098122.tarea_room.data.model.Option

@Composable
fun OptionItem(
  option: Option,
  onDelete: () -> Unit,
  onEdit: (Option) -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth(),
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
  ) {
    Row(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
      AsyncImage(
        model = option.imageUrl,
        contentDescription = option.value,
        modifier = Modifier
          .size(width = 80.dp, height = 120.dp)
          .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
      )
      Spacer(modifier = Modifier.width(12.dp))
      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = option.value,
          style = MaterialTheme.typography.titleMedium,
          fontWeight = FontWeight.Bold,
          maxLines = 2,
          overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = "Puntuacion: ${option.votes} /100",
          style = MaterialTheme.typography.bodyMedium
        )
        
      }
      Column(modifier = Modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        
        IconButton(onClick = { onDelete() }) {
          Icon(
            imageVector = Icons.Default.DeleteOutline,
            contentDescription = "Borrar ${option.value}",
            tint = MaterialTheme.colorScheme.error
          )
        }
        IconButton(onClick = { onEdit(option) }) {
          Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Editar ${option.value}",
            tint = MaterialTheme.colorScheme.primary
          )
        }
      }
    }
  }
}
