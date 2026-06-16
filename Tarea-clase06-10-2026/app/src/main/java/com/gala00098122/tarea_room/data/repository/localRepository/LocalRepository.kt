package com.gala00098122.tarea_room.data.repository.localRepository

import com.gala00098122.tarea_room.data.model.Local
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
  fun getLocals(questionId: Int): Flow<List<Local>>
  suspend fun addLocal(name: String, imageUrl: String, votes: Int, questionId: Int)
  suspend fun deleteLocal(local: Local)
}