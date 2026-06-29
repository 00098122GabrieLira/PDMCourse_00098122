package com.gala00098122.tarea_room.data.remote.questions

import com.gala00098122.tarea_room.data.api.KtorClient
import com.gala00098122.tarea_room.data.api.questions.PostQuestionRequestDTO
import com.gala00098122.tarea_room.data.api.questions.PostQuestionResponseDTO
import com.gala00098122.tarea_room.data.api.questions.QuestionDTO
import com.gala00098122.tarea_room.data.api.questions.UpdateQuestionRequestDTO
import com.gala00098122.tarea_room.data.api.questions.UpdateQuestionResponseDTO
import com.gala00098122.tarea_room.data.api.questions.toEntity
import com.gala00098122.tarea_room.data.database.dao.QuestionDAO
import com.gala00098122.tarea_room.data.database.entities.QuestionEntity
import com.gala00098122.tarea_room.data.database.entities.toModel
import com.gala00098122.tarea_room.data.model.Question
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuestionsOfflineRepositoryImpl(private val questionDAO: QuestionDAO) :
  QuestionsOfflineRepository {
  
  override fun getQuestions(): Flow<List<Question>> {
    return questionDAO.getQuestionsWithOptions().map { list -> list.map { it.toModel() } }
  }
  
  override suspend fun createQuestion(text: String): Result<Int> {
    try {
      val request = PostQuestionRequestDTO(
        title = text
      )
      val response: PostQuestionResponseDTO = KtorClient.client.post("questions") {
        header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        setBody(request)
      }.body()
      
      return Result.success(response.id)
    } catch (e: Exception) {
      return Result.failure(e)
    }
  }
  
  override suspend fun updateQuestion(id: Int, text: String): Result<Int> {
    try {
      val request = UpdateQuestionRequestDTO(
        id = id,
        title = text
      )
      val response: UpdateQuestionResponseDTO = KtorClient.client.put("questions/${id}") {
        header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        setBody(request)
      }.body()
      
      return Result.success(response.id)
    } catch (e: Exception) {
      return Result.failure(e)
    }
  }
  
  override suspend fun deleteQuestion(id: Int) {
    try {
      val response = KtorClient.client.delete("questions/${id}") {}
      
      if (response.status.value == 200) {
        val question = QuestionEntity(id = id, title = "")
        questionDAO.delete(question)
      }
      
    } catch (e: Exception) {
      e.printStackTrace()
    }
    
  }
  
  override suspend fun refresh() {
    val questions = fetchQuestions()
    return questionDAO.upsert(questions.map { it.toEntity() })
  }
}

private suspend fun fetchQuestions(): List<QuestionDTO> =
  KtorClient.client.get("questions") {
  
  }.body()


