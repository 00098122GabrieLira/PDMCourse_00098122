package com.gala00098122.tarea_room.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.gala00098122.tarea_room.data.database.entities.QuestionEntity
import com.gala00098122.tarea_room.data.database.entities.QuestionWithOptions
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDAO {
  
  @Query("SELECT * FROM questions ORDER BY title COLLATE NOCASE ASC")
  fun getQuestionsWithOptions(): Flow<List<QuestionWithOptions>>
  
  @Upsert
  suspend fun upsert(question: List<QuestionEntity>)
  
  @Delete
  suspend fun delete(question: QuestionEntity)
  
}