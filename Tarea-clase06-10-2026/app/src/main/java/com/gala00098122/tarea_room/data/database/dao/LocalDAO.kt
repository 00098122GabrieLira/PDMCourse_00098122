package com.gala00098122.tarea_room.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gala00098122.tarea_room.data.database.entities.LocalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDAO {
  
  @Query("SELECT * FROM locals")
  fun getAllLocals(): Flow<List<LocalEntity>>
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertLocal(local: LocalEntity)
  
  @Delete
  suspend fun deleteLocal(local: LocalEntity)
}