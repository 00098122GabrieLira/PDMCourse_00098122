package com.gala00098122.tarea_room.data.repository.questionRepository

import com.gala00098122.tarea_room.data.model.Question
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {
  fun getQuestions(): Flow<List<Question>>
  suspend fun addQuestion(title: String)
  suspend fun deleteQuestion(question: Question)
}