package com.gala00098122.postapp.ui.screens.jsonListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gala00098122.postapp.data.repositories.JsonApiRepository
import com.gala00098122.postapp.data.repositories.JsonRepository
import com.gala00098122.postapp.model.Json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class JsonListViewModel : ViewModel() {
  private val jsonRepository: JsonRepository = JsonApiRepository()
  
  private val _json = MutableStateFlow<List<Json>>(emptyList())
  val json = _json.asStateFlow()
  
  private val _loading = MutableStateFlow<Boolean>(false)
  val loading = _loading.asStateFlow()
  
  private val _error = MutableStateFlow<String?>(null)
  val error = _error.asStateFlow()
  
  private val _refresh = MutableStateFlow<Boolean>(false)
  val refresh = _refresh.asStateFlow()
  
  init {
    loadJson()
  }
  
  fun loadJson() {
    viewModelScope.launch {

      _error.value = null
      _loading.value = true
      
      jsonRepository.getJson()
        .onSuccess { json ->
          _json.value = json
        }
        .onFailure { error ->
          _error.value =
            "Ocurrió un error al cargar los datos. Por favor, intenta recargar la página."
        }
      
      _loading.value = false
    }
  }
  
  fun refreshJson() {
    viewModelScope.launch {
      _error.value = null
      _refresh.value = true
      
      jsonRepository.getJson()
        .onSuccess { json ->
          _json.value = json
        }
        .onFailure { error ->
          _error.value =
            "Ocurrió un error al cargar los datos. Por favor, intenta recargar la página."
        }
      
      _refresh.value = false
    }
  }
}