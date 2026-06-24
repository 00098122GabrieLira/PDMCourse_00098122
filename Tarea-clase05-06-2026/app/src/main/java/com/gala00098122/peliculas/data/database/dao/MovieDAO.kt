package com.gala00098122.peliculas.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gala00098122.peliculas.data.database.entities.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {
  @Query("SELECT * FROM favorite_movie ORDER BY title COLLATE NOCASE ASC")
  fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>>
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertMovie(favoriteMovie: FavoriteMovieEntity)
  
  @Delete
  suspend fun deleteMovie(favoriteMovie: FavoriteMovieEntity)
  
  @Query("""SELECT EXISTS(SELECT 1 FROM favorite_movie WHERE id = :movieId)""")
  fun isFavorite(movieId: Int): Flow<Boolean>
}