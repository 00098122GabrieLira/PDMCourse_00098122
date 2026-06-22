package com.gala00098122.tarea_room.screens.options

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.gala00098122.tarea_room.TareaRoomApplication
import com.gala00098122.tarea_room.data.repository.optionRepository.OptionRepository
import com.gala00098122.tarea_room.data.model.Option
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class OptionsViewModel(
  private val optionRepository: OptionRepository,
  private val questionId: Int) :
  ViewModel() {
  
  val options: StateFlow<List<Option>> = optionRepository.getOptions(questionId)
    .stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = emptyList()
  )
  
  fun addOption(name: String, imageUrl: String, votes: Int) {
    viewModelScope.launch {
      optionRepository.addOption(name, imageUrl, votes, questionId)
    }
  }
  
  fun deleteOption(option: Option) {
    viewModelScope.launch {
      optionRepository.deleteOption(option)
    }
  }
  
  fun updateOption(option:Option){
    viewModelScope.launch {
      optionRepository.updateOption(option)
    }
  }
  
  companion object {
    fun provideFactory(questionId: Int) = viewModelFactory {
      initializer {
        val app = this[APPLICATION_KEY] as TareaRoomApplication
        OptionsViewModel(app.appProvider.provideOptionRepository(), questionId)
      }
    }
  }
}