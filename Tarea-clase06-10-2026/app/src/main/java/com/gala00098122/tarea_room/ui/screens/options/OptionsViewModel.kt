package com.gala00098122.tarea_room.ui.screens.options

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.gala00098122.tarea_room.TareaRoomApplication
import com.gala00098122.tarea_room.data.model.Option
import com.gala00098122.tarea_room.data.remote.options.OptionsOfflineRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class OptionsViewModel(
  private val optionRepository: OptionsOfflineRepository,
  private val questionId: Int) :
  ViewModel() {
  
  val options: StateFlow<List<Option>> = optionRepository.getOptions(questionId)
    .stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = emptyList()
  )
  
  private val _isRefreshing = MutableStateFlow(false)
  val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()
  
  private val _error = MutableStateFlow<String?>(null)
  val error: StateFlow<String?> = _error.asStateFlow()
  
  init{
    refresh()
  }
  
  fun refresh() {
    viewModelScope.launch {
      _error.value = null
      _isRefreshing.value = true
      
      try {
        optionRepository.refresh(questionId)
      } catch (_: Exception) {
        if (options.value.isEmpty()) {
          _error.value = "Sin conexión y sin datos en caché"
        }
      }
      _isRefreshing.value = false
    }
  }
  
  fun addOption(value: String, imageUrl: String) {
    viewModelScope.launch {
      optionRepository.createOption(questionId, value, imageUrl)
    }
  }
  
  fun deleteOption(option: Option) {
    viewModelScope.launch {
      optionRepository.deleteOption(option.id)
    }
  }
  
  fun updateOption(option:Option){
    viewModelScope.launch {
      optionRepository.updateOption(option.id, option.value)
    }
  }
  
  companion object {
    fun provideFactory(questionId: Int) = viewModelFactory {
      initializer {
        val app = this[APPLICATION_KEY] as TareaRoomApplication
        OptionsViewModel(app.appProvider.provideOptionsOfflineRepository(), questionId)
      }
    }
  }
}