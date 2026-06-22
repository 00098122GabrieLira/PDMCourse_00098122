package com.gala00098122.tarea_room.screens.questions.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.gala00098122.tarea_room.data.model.Question

@Composable
fun QuestionItem(
  question: Question,
  onDelete: () -> Unit,
  onClick: () -> Unit,
  onEdit: (Question) -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onClick() },
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
  ) {
    Row(
      modifier = Modifier
        .padding(12.dp)
    ) {
      
      Text(
        text = question.title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
      )
      
      Spacer(modifier = Modifier.weight(1f))
      
      IconButton(onClick = { onDelete() }) {
        Icon(
          imageVector = Icons.Default.DeleteOutline,
          contentDescription = "Borrar ${question.title}",
          tint = MaterialTheme.colorScheme.error
        )
      }
      
      IconButton(onClick = { onEdit(question) }) {
        Icon(
          imageVector = Icons.Default.Edit,
          contentDescription = "Editar ${question.title}",
          tint = MaterialTheme.colorScheme.primary
        )
      }
      
    }
  }
}
