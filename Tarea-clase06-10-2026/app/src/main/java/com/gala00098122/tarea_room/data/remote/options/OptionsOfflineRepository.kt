package com.gala00098122.tarea_room.data.remote.options

import com.gala00098122.tarea_room.data.model.Option
import kotlinx.coroutines.flow.Flow

interface OptionsOfflineRepository {
  fun getOptions(questionId: Int): Flow<List<Option>>
  suspend fun refresh(questionId: Int)
  suspend fun createOption(questionId: Int, value: String, imageUrl: String): Result<Int>
  suspend fun updateOption(id: Int, value: String): Result<Int>
  suspend fun deleteOption(id: Int)
}