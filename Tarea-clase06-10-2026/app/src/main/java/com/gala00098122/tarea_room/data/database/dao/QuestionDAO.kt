package com.gala00098122.tarea_room.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.gala00098122.tarea_room.data.database.entities.QuestionEntity
import com.gala00098122.tarea_room.data.database.entities.QuestionWithOptions
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDAO {
  
  @Transaction
  @Query("SELECT * FROM questions")
  fun getQuestionsWithOptions(): Flow<List<QuestionWithOptions>>
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertQuestion(question: QuestionEntity)
  
  @Delete
  suspend fun deleteQuestion(question: QuestionEntity)
}