package com.gala00098122.tarea_room.data.repository.optionRepository

import com.gala00098122.tarea_room.data.model.Option
import kotlinx.coroutines.flow.Flow

interface OptionRepository {
  fun getOptions(questionId: Int): Flow<List<Option>>
  suspend fun addOption(name: String, imageUrl: String? = null, votes: Int, questionId: Int)
  suspend fun deleteOption(option: Option)
  suspend fun updateOption(option: Option)
}