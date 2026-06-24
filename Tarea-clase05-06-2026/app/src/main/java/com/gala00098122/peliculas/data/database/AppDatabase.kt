package com.gala00098122.peliculas.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.gala00098122.peliculas.data.database.dao.MovieDAO
import com.gala00098122.peliculas.data.database.entities.FavoriteMovieEntity
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

@Database(
  entities = [FavoriteMovieEntity::class],
  version = 1,
  exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
  
  abstract fun favoriteMovieDAO(): MovieDAO
  
  companion object {
    @Volatile
    private var INSTANCE: AppDatabase? = null
    
    fun getDatabase(context: Context): AppDatabase {
      return INSTANCE ?: synchronized(this) {
        Room.databaseBuilder(
          context = context.applicationContext,
          klass = AppDatabase::class.java,
          name = "FavoriteMovies_database"
        )
          .fallbackToDestructiveMigration(false)
          .build()
          .also { INSTANCE = it }
      }
    }
  }
}

class Converters {
  
  @TypeConverter
  fun fromIntList(value: List<Int>): String {
    return Gson().toJson(value)
  }
  
  @TypeConverter
  fun toIntList(value: String): List<Int> {
    val listType = object : TypeToken<List<Int>>() {}.type
    return Gson().fromJson(value, listType)
  }
}
