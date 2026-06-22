package com.gala00098122.tarea_room.data.repository.optionRepository

import com.gala00098122.tarea_room.data.database.dao.OptionDAO
import com.gala00098122.tarea_room.data.database.entities.toEntity
import com.gala00098122.tarea_room.data.database.entities.toModel
import com.gala00098122.tarea_room.data.model.Option
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.collections.map

class OptionRepositoryImpl(private val optionDAO: OptionDAO) : OptionRepository {
  
  override fun getOptions(questionId: Int): Flow<List<Option>> {
    return optionDAO.getOptionsForQuestion(questionId).map { entities ->
      entities.map { it.toModel() }
    }
  }
  
  override suspend fun addOption(name: String, imageUrl: String?, votes: Int, questionId: Int) {
    val option = Option(value = name, imageUrl = imageUrl, votes = votes, questionId = questionId)
    optionDAO.insertOption(option.toEntity())
  }
  
  override suspend fun deleteOption(option: Option) {
    optionDAO.deleteOption(option.toEntity())
  }
  
  override suspend fun updateOption(option: Option) {
    optionDAO.updateOption(option.toEntity())
  }
  
}
