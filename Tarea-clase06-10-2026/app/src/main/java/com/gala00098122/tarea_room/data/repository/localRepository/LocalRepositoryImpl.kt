package com.gala00098122.tarea_room.data.repository.localRepository

import com.gala00098122.tarea_room.data.database.dao.LocalDAO
import com.gala00098122.tarea_room.data.database.entities.toEntity
import com.gala00098122.tarea_room.data.database.entities.toModel
import com.gala00098122.tarea_room.data.model.Local
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.collections.map

class LocalRepositoryImpl(private val localDAO: LocalDAO) : LocalRepository {
  
  override fun getLocals(questionId: Int): Flow<List<Local>> {
    return localDAO.getLocalsForQuestion(questionId).map { entities ->
      entities.map { it.toModel() }
    }
  }
  
  override suspend fun addLocal(name: String, imageUrl: String, votes: Int, questionId: Int) {
    val local = Local(name = name, imageUrl = imageUrl, votes = votes, questionId = questionId)
    localDAO.insertLocal(local.toEntity())
  }
  
  override suspend fun deleteLocal(local: Local) {
    localDAO.deleteLocal(local.toEntity())
  }
}
