package com.gala00098122.tarea_room.data.repository.localRepository

import com.gala00098122.tarea_room.data.model.Local
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
  fun getLocals(): Flow<List<Local>>
  suspend fun addLocal(local: Local)
  suspend fun deleteLocal(local: Local)
}