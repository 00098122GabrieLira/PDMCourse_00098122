package com.gala00098122.tarea_room.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gala00098122.tarea_room.data.database.dao.OptionDAO
import com.gala00098122.tarea_room.data.database.dao.QuestionDAO
import com.gala00098122.tarea_room.data.database.entities.OptionEntity
import com.gala00098122.tarea_room.data.database.entities.QuestionEntity

@Database(
  entities = [OptionEntity::class, QuestionEntity::class],
  version = 4,
  exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
  
  abstract fun optionDAO(): OptionDAO
  abstract fun questionDAO(): QuestionDAO
  
  companion object {
    @Volatile
    private var INSTANCE: AppDatabase? = null
    
    fun getDatabase(context: Context): AppDatabase {
      return INSTANCE ?: synchronized(this) {
        Room.databaseBuilder(
          context = context.applicationContext,
          klass = AppDatabase::class.java,
          name = "rankeuca_database"
        )
          .fallbackToDestructiveMigration(false)
          .build()
          .also { INSTANCE = it }
      }
    }
  }
}