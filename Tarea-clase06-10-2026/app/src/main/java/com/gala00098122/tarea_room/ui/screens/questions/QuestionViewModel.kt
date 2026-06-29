package com.gala00098122.tarea_room.ui.screens.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.gala00098122.tarea_room.TareaRoomApplication
import com.gala00098122.tarea_room.data.model.Question
import com.gala00098122.tarea_room.data.remote.questions.QuestionsOfflineRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class QuestionViewModel(
  private val questionRepository: QuestionsOfflineRepository,
) :
  ViewModel() {
  
  val questions: StateFlow<List<Question>> = questionRepository.getQuestions()
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
        questionRepository.refresh()
      } catch (_: Exception) {
        if (questions.value.isEmpty()) {
          _error.value = "Sin conexión y sin datos en caché"
        }
      }
      _isRefreshing.value = false
    }
  }
  
  fun addQuestion(title: String) {
    viewModelScope.launch {
      questionRepository.createQuestion(title)
    }
  }
  
  fun deleteQuestion(question: Question) {
    viewModelScope.launch {
      questionRepository.deleteQuestion(question.id)
    }
  }
  
  fun updateQuestion(question: Question) {
    viewModelScope.launch {
      questionRepository.updateQuestion(question.id, question.title)
    }
  }
  
  companion object {
    fun provideFactory() = viewModelFactory {
      initializer {
        val app = this[APPLICATION_KEY] as TareaRoomApplication
        QuestionViewModel(app.appProvider.provideQuestionOfflineRepository())
      }
    }
  }
}