package com.gala00098122.peliculas.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gala00098122.peliculas.data.model.Movie
import kotlin.Int

@Entity(tableName = "favorite_movie")
data class FavoriteMovieEntity(
  @PrimaryKey(autoGenerate = false)
  val id: Int,
  val title: String,
  val originalTitle: String,
  val originalLanguage: String,
  val overview: String,
  val releaseDate: String,
  val adult: Boolean,
  val genreIds: List<Int> = emptyList(),
  val popularity: Double,
  val voteAverage: Double,
  val voteCount: Int,
  val softcore: Boolean,
  val video: Boolean,
  val backdropUrl: String,
  val posterUrl: String
)

fun Movie.toEntity(): FavoriteMovieEntity {
  return FavoriteMovieEntity(
    id = id,
    title = title,
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    overview = overview,
    releaseDate = releaseDate,
    adult = adult,
    genreIds = genreIds,
    popularity = popularity,
    voteAverage = voteAverage,
    voteCount = voteCount,
    softcore = softcore,
    video = video,
    backdropUrl = backdropUrl,
    posterUrl = posterUrl
  )
}

fun FavoriteMovieEntity.toModel(): Movie{
  return Movie(
    id = id,
    title = title,
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    overview = overview,
    releaseDate = releaseDate,
    adult = adult,
    genreIds = genreIds,
    popularity = popularity,
    voteAverage = voteAverage,
    voteCount = voteCount,
    softcore = softcore,
    video = video,
    backdropUrl = backdropUrl,
    posterUrl = posterUrl
  )
}