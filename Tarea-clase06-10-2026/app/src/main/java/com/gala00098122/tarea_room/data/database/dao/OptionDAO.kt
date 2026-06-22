package com.gala00098122.tarea_room.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gala00098122.tarea_room.data.database.entities.OptionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OptionDAO {
  
  @Query("SELECT * FROM options WHERE questionId = :questionId")
  fun getOptionsForQuestion(questionId: Int): Flow<List<OptionEntity>>
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertOption(option: OptionEntity)
  
  @Delete
  suspend fun deleteOption(option: OptionEntity)
  
  @Update
  suspend fun updateOption(option: OptionEntity)
  
}