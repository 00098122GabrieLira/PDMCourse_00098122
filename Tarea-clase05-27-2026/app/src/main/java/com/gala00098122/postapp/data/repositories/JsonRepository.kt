package com.gala00098122.postapp.data.repositories

import com.gala00098122.postapp.model.Json

interface JsonRepository {
  suspend fun getJson(): Result<List<Json>>
  suspend fun postJson(json: Json): Result<Json>
}