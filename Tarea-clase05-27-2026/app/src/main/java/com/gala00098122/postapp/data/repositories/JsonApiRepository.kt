package com.gala00098122.postapp.data.repositories

import com.gala00098122.postapp.data.api.KtorClient
import com.gala00098122.postapp.data.api.getJson.CreatePostRequestDTO
import com.gala00098122.postapp.data.api.getJson.JsonDTO
import com.gala00098122.postapp.data.api.getJson.toModel
import com.gala00098122.postapp.model.Json
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class JsonApiRepository : JsonRepository {
  override suspend fun getJson(): Result<List<Json>> {
    try {
      val response: List<JsonDTO> = KtorClient.client.get("posts") {
      
      }.body()
      
      return Result.success(response.map { jsonDTO -> jsonDTO.toModel() })
    } catch (e: Exception) {
      return Result.failure(e)
    }
  }
  
  override suspend fun postJson(json: Json): Result<Json> {
    try {
      val request = CreatePostRequestDTO(
        userId = json.userId,
        title = json.title,
        body = json.body
      )
      val response: JsonDTO = KtorClient.client.post("posts") {
        contentType(ContentType.Application.Json)
        setBody(request)
      }.body()
      
      return Result.success(response.toModel())
    } catch (e: Exception) {
      return Result.failure(e)
    }
  }
}