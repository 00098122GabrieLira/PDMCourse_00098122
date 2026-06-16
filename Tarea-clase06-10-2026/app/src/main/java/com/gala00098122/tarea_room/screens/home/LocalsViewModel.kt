package com.gala00098122.tarea_room.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.gala00098122.tarea_room.TareaRoomApplication
import com.gala00098122.tarea_room.data.repository.localRepository.LocalRepository
import com.gala00098122.tarea_room.data.model.Local
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LocalsViewModel(
  private val localRepository: LocalRepository,
  private val questionId: Int) :
  ViewModel() {
  
  val locals: StateFlow<List<Local>> = localRepository.getLocals(questionId)
    .stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = emptyList()
  )
  
  fun addLocal(name: String, imageUrl: String, votes: Int) {
    viewModelScope.launch {
      localRepository.addLocal(name, imageUrl, votes, questionId)
    }
  }
  
  fun deleteLocal(local: Local) {
    viewModelScope.launch {
      localRepository.deleteLocal(local)
    }
  }
  
  companion object {
    fun provideFactory(questionId: Int) = viewModelFactory {
      initializer {
        val app = this[APPLICATION_KEY] as TareaRoomApplication
        LocalsViewModel(app.appProvider.provideLocalRepository(), questionId)
      }
    }
  }
}