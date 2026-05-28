package com.gala00098122.postapp.ui.screens.postJsonScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gala00098122.postapp.model.Json
import com.gala00098122.postapp.scafold.AppScaffold

@Composable
fun JsonPostScreen(
  navigateBack: () -> Unit,
  viewModel: JsonPostViewModel = viewModel()
) {
  val jsons by viewModel.jsons.collectAsState()
  val error by viewModel.error.collectAsState()
  val context = LocalContext.current
  var userId by rememberSaveable { mutableIntStateOf(0) }
  var title by rememberSaveable { mutableStateOf("") }
  var body by rememberSaveable { mutableStateOf("") }
  
  fun boton() {
    when {
      
      (userId.toString().isBlank() || title.isBlank() || body.isBlank()) -> {
        Toast.makeText(
          context,
          "Por favor llenar todos los campos",
          Toast.LENGTH_SHORT
        ).show()
      }
      
      error != null -> {
        Toast.makeText(
          context,
          error,
          Toast.LENGTH_SHORT
        ).show()
      }
      
      else -> {
        val newJson = Json(
          id = (jsons.size + 1),
          userId = userId,
          title = title,
          body = body
        )
        viewModel.addData(newJson)
        
        // Limpiar los campos
        userId = 0
        title = ""
        body = ""
        
        Toast.makeText(
          context,
          "Datos enviados con éxito",
          Toast.LENGTH_SHORT
        ).show()
      }
    }
  }
  
  AppScaffold(
    title = "Crear Json",
    navigationIcon = {
      IconButton(onClick = { navigateBack() }) {
        Icon(
          Icons.AutoMirrored.Filled.ArrowBack,
          contentDescription = "Volver"
        )
      }
    }
  ) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(padding)
    ) {
      OutlinedTextField(
        value = if (userId == 0) "" else userId.toString(),
        onValueChange = { userId = it.toIntOrNull() ?: 0 },
        label = { Text(text = "Id de Usuario") },
        placeholder = { Text(text = "Ingresa el ID de usuario") },
        modifier = Modifier
          .fillMaxWidth()
          .padding(10.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
          keyboardType = KeyboardType.Number,
          imeAction = ImeAction.Next
        )
      )
      
      OutlinedTextField(
        value = title,
        onValueChange = { title = it },
        label = { Text(text = "Título") },
        placeholder = { Text(text = "Ingresa un título") },
        modifier = Modifier
          .fillMaxWidth()
          .padding(10.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
          keyboardType = KeyboardType.Text,
          imeAction = ImeAction.Next
        )
      )
      
      OutlinedTextField(
        value = body,
        onValueChange = { body = it },
        label = { Text(text = "Contenido") },
        placeholder = { Text(text = "Ingresa el contenido del post") },
        modifier = Modifier
          .fillMaxWidth()
          .padding(10.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
          keyboardType = KeyboardType.Text,
          imeAction = ImeAction.Done
        )
      )
      
      Button(
        onClick = { boton() },
        colors = ButtonDefaults.buttonColors(
          containerColor = MaterialTheme.colorScheme.primary,
          contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = Modifier
          .fillMaxWidth()
          .padding(10.dp)
      ) {
        Text(text = "Publicar post")
      }
    }
  }
}