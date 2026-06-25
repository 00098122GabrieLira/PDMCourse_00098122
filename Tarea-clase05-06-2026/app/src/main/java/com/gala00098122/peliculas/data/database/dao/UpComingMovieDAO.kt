package com.gala00098122.peliculas.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.gala00098122.peliculas.data.database.entities.UpComingMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UpComingMovieDAO {
  @Query("SELECT * FROM upComing_movie ORDER BY title COLLATE NOCASE ASC")
  fun getUpComingMovies(): Flow<List<UpComingMovieEntity>>
  
  @Query("SELECT * FROM upComing_movie WHERE id = :id")
  fun getUpComingMovieById(id: Int): Flow<UpComingMovieEntity?>
  
  @Upsert
  suspend fun upsert(movie: UpComingMovieEntity)
  
  @Upsert
  suspend fun upsertAll(movies: List<UpComingMovieEntity>)
}