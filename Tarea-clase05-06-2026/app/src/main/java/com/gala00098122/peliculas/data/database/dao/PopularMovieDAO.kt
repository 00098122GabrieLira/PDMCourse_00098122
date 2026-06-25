package com.gala00098122.peliculas.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.gala00098122.peliculas.data.database.entities.PopularMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularMovieDAO {
  @Query("SELECT * FROM popular_movie ORDER BY title COLLATE NOCASE ASC")
  fun getPopularMovies(): Flow<List<PopularMovieEntity>>
  
  @Query("SELECT * FROM popular_movie WHERE id = :id")
  fun getPopularMovieById(id: Int): Flow<PopularMovieEntity?>
  
  @Upsert
  suspend fun upsert(movie: PopularMovieEntity)
  
  @Upsert
  suspend fun upsertAll(movies: List<PopularMovieEntity>)
}