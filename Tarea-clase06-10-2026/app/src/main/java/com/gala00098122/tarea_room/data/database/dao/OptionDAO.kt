package com.gala00098122.tarea_room.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.gala00098122.tarea_room.data.database.entities.OptionEntity
import com.gala00098122.tarea_room.data.database.entities.QuestionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OptionDAO {
  
  @Query("SELECT * FROM options WHERE questionId = :questionId ORDER BY value COLLATE NOCASE ASC")
  fun getOptionsForQuestion(questionId: Int): Flow<List<OptionEntity>>
  
  @Upsert
  suspend fun upsert(option: List<OptionEntity>)
  
  @Query("DELETE FROM options WHERE id = :id")
  suspend fun delete(id: Int)
  
}