package com.gala00098122.tarea_room.data.remote.questions

import com.gala00098122.tarea_room.data.model.Question
import kotlinx.coroutines.flow.Flow

interface QuestionsOfflineRepository {
  fun getQuestions(): Flow<List<Question>>
  suspend fun refresh()
  suspend fun createQuestion(text: String): Result<Int>
  suspend fun updateQuestion(id: Int, text: String) : Result<Int>
  suspend fun deleteQuestion(id: Int)
}