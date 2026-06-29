package com.gala00098122.tarea_room.ui.screens.questions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Inbox
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gala00098122.tarea_room.data.model.Question
import com.gala00098122.tarea_room.scaffold.AppScaffold
import com.gala00098122.tarea_room.ui.screens.questions.components.QuestionBottomSheet
import com.gala00098122.tarea_room.ui.screens.questions.components.QuestionItem

@Composable
fun QuestionScreen(
  navigateBack: () -> Unit,
  navigateToOptions: (Int) -> Unit,
  viewModel: QuestionViewModel = viewModel(factory = QuestionViewModel.provideFactory())
) {
  val questions by viewModel.questions.collectAsState()
  val isRefreshing by viewModel.isRefreshing.collectAsState()
  val error by viewModel.error.collectAsState()
  var showSheet by rememberSaveable { mutableStateOf(false) }
  var editingQuestion by rememberSaveable { mutableStateOf<Question?>(null) }
  
  if (questions.isEmpty() && isRefreshing) {
    AppScaffold(title = "Cargando...") { padding ->
      CircularProgressIndicator(modifier = Modifier.padding(padding))
    }
    return
  }
  
  if (questions.isEmpty() && error != null) {
    AppScaffold(title = "Preguntas") { padding ->
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
          onClick = { viewModel.refresh() },
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
    title = "Administrar preguntas",
    navigationIcon = {
      IconButton(onClick = { navigateBack() }) {
        Icon(
          imageVector = Icons.AutoMirrored.Filled.ArrowBack,
          contentDescription = "Back"
        )
      }
    },
    actions = {
      IconButton(onClick = { showSheet = true }) {
        Icon(
          imageVector = Icons.Default.Add,
          contentDescription = "Agregar local"
        )
      }
    }
  ) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(padding)
        .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
      
      if (questions.isEmpty()) {
        Column(
          modifier = Modifier.fillMaxSize(),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterVertically)
        ) {
          Icon(
            imageVector = Icons.Default.Inbox,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier.height(36.dp)
          )
          
          Text(
            text = "Todavia no hay preguntas",
            style = MaterialTheme.typography.titleMedium
          )
          Text(
            text = "Toca + para crear una nueva.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      } else {
        PullToRefreshBox(
          isRefreshing = isRefreshing,
          onRefresh = { viewModel.refresh() },
          modifier = Modifier.fillMaxSize()
        ) {
          
          LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            items(items = questions, key = { it.id }) { question ->
              QuestionItem(
                question = question,
                onDelete = { viewModel.deleteQuestion(question) },
                onClick = { navigateToOptions(question.id) },
                onEdit = { editingQuestion = it }
              )
            }
          }
        }
      }
    }
  }
  
  if (showSheet) {
    QuestionBottomSheet(
      onSave = { title ->
        viewModel.addQuestion(title)
      },
      onDismiss = { showSheet = false }
    )
  }
  
  if (editingQuestion != null) {
    QuestionBottomSheet(
      initialQuestion = editingQuestion,
      onSave = { title ->
        
        val updatedQuestion = editingQuestion?.copy(
          title = title
        )
        
        updatedQuestion?.let { viewModel.updateQuestion(it) }
        
        editingQuestion = null
      },
      onDismiss = {
        editingQuestion = null
      }
    )
  }
  
}
