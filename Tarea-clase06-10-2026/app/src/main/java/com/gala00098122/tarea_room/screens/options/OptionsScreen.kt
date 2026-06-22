package com.gala00098122.tarea_room.screens.options

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gala00098122.tarea_room.data.model.Option
import com.gala00098122.tarea_room.scaffold.AppScaffold
import com.gala00098122.tarea_room.screens.options.components.OptionBottomSheet
import com.gala00098122.tarea_room.screens.options.components.OptionItem

@Composable
fun OptionsScreen(
  questionId: Int,
  navigateBack: () -> Unit,
  viewModel: OptionsViewModel = viewModel(
    key = questionId.toString(),
    factory = OptionsViewModel.provideFactory(questionId)
  )
) {
  
  val options by viewModel.options.collectAsStateWithLifecycle()
  var showSheet by rememberSaveable { mutableStateOf(false) }
  var editingOption by rememberSaveable { mutableStateOf<Option?>(null) }
  
  AppScaffold(
    title = "Administrar opciones",
    actions = {
      IconButton(onClick = { showSheet = true }) {
        Icon(
          imageVector = Icons.Default.Add,
          contentDescription = "Agregar opción"
        )
      }
    },
    navigationIcon = {
      IconButton(onClick = { navigateBack() }) {
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
        .padding(padding)
        .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
      
      if (options.isEmpty()) {
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
            text = "Todavia no hay opciones",
            style = MaterialTheme.typography.titleMedium
          )
          Text(
            text = "Toca + para crear una nueva.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      } else {
        LazyColumn(
          modifier = Modifier.fillMaxSize(),
          contentPadding = PaddingValues(vertical = 4.dp),
          verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          items(items = options, key = { it.id }) { local ->
            OptionItem(
              option = local,
              onDelete = { viewModel.deleteOption(local) },
              onEdit = { editingOption = it }
            )
            Spacer(modifier = Modifier.height(4.dp))
          }
        }
      }
    }
  }
  
  if (showSheet) {
    OptionBottomSheet(
      initialOption = null,
      onSave = { name, imageUrl, votes ->
        viewModel.addOption(name, imageUrl, votes)
      },
      onDismiss = { showSheet = false }
    )
  }
  
  if (editingOption != null) {
    OptionBottomSheet(
      initialOption = editingOption,
      onSave = { name, imageUrl, votes ->
        
        val updatedLocal = editingOption?.copy(
          value = name,
          imageUrl = imageUrl,
          votes = votes
        )
        
        updatedLocal?.let { viewModel.updateOption(it) }
        
        editingOption = null
      },
      onDismiss = {
        editingOption = null
      }
    )
  }
  
}