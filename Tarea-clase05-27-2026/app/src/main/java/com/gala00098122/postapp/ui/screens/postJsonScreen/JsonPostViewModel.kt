package com.gala00098122.postapp.ui.screens.postJsonScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gala00098122.postapp.data.repositories.JsonApiRepository
import com.gala00098122.postapp.data.repositories.JsonRepository
import com.gala00098122.postapp.model.Json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class JsonPostViewModel : ViewModel() {
  private val jsonRepository: JsonRepository = JsonApiRepository()
  
  private val _jsons = MutableStateFlow<MutableList<Json>>(mutableListOf())
  val jsons = _jsons.asStateFlow()
  
  private val _error = MutableStateFlow<String?>(null)
  val error = _error.asStateFlow()
  
  fun addData(json: Json) {
    viewModelScope.launch {
      jsonRepository.postJson(json)
        .onSuccess { json ->
          
          val currentList = _jsons.value.toMutableList()
          currentList.add(json)
          
          _jsons.value = currentList
        }
        .onFailure {
          _error.value = "Ocurrió un error al enviar los datos. Por favor, intenta nuevamente."
        }
    }
  }
}