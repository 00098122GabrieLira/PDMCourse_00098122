package com.gala00098122.tarea_room.data.repository.questionRepository

import com.gala00098122.tarea_room.data.database.dao.QuestionDAO
import com.gala00098122.tarea_room.data.database.entities.QuestionEntity
import com.gala00098122.tarea_room.data.database.entities.toEntity
import com.gala00098122.tarea_room.data.database.entities.toModel
import com.gala00098122.tarea_room.data.model.Question
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuestionRepositoryImpl(private val questionDao: QuestionDAO) : QuestionRepository {
  
  override fun getQuestions(): Flow<List<Question>> {
    return questionDao.getQuestionsWithOptions().map { list ->
      list.map { it.toModel() }
    }
  }
  
  override suspend fun addQuestion(title: String) {
    questionDao.insertQuestion(QuestionEntity(title = title))
  }
  
  override suspend fun deleteQuestion(question: Question) {
    questionDao.deleteQuestion(question.toEntity())
  }
}