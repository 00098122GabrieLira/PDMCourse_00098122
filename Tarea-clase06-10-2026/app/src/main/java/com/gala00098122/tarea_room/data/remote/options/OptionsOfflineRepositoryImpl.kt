package com.gala00098122.tarea_room.data.remote.options

import com.gala00098122.tarea_room.data.api.KtorClient
import com.gala00098122.tarea_room.data.database.dao.OptionDAO
import com.gala00098122.tarea_room.data.database.entities.toModel
import com.gala00098122.tarea_room.data.model.Option
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.gala00098122.tarea_room.data.api.options.OptionsDTO
import com.gala00098122.tarea_room.data.api.options.PostOptionRequestDTO
import com.gala00098122.tarea_room.data.api.options.PostOptionResponseDTO
import com.gala00098122.tarea_room.data.api.options.UpdateOptionRequestDTO
import com.gala00098122.tarea_room.data.api.options.UpdateOptionResponseDTO
import com.gala00098122.tarea_room.data.api.options.toEntity
import com.gala00098122.tarea_room.data.database.entities.OptionEntity
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders

class OptionsOfflineRepositoryImpl(private val optionDAO: OptionDAO) : OptionsOfflineRepository {
  
  override fun getOptions(questionId: Int): Flow<List<Option>> {
    return optionDAO.getOptionsForQuestion(questionId).map { list -> list.map { it.toModel() } }
  }
  
  override suspend fun refresh(questionId: Int) {
    val options = fetchOptions(questionId)
    optionDAO.upsert(options.map { it.toEntity() })
  }
  
  override suspend fun createOption(questionId: Int, value: String, imageUrl: String): Result<Int> {
    try {
      val request = PostOptionRequestDTO(
        name = value,
        imageUrl = imageUrl,
        questionId = questionId
      )
      
      val response: PostOptionResponseDTO = KtorClient.client.post("options") {
        header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        setBody(request)
      }.body()
      
      return Result.success(response.id)
    } catch (e: Exception) {
      return Result.failure(e)
    }
  }
  
  override suspend fun deleteOption(id: Int) {
    try {
      val response = KtorClient.client.delete("options/${id}")
      
      if (response.status.value == 200) {
        optionDAO.delete(id)
      }
      
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }
  
  override suspend fun updateOption(id: Int, value: String): Result<Int> {
    try {
      val request = UpdateOptionRequestDTO(
        name = value
      )
      
      val response: UpdateOptionResponseDTO = KtorClient.client.put("options/${id}") {
        header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        setBody(request)
      }.body()
      
      return Result.success(response.id)
    } catch (e: Exception) {
      return Result.failure(e)
    }
  }
}

private suspend fun fetchOptions(questionId: Int): List<OptionsDTO> =
  KtorClient.client.get("options?questionId=${questionId}") {}.body()