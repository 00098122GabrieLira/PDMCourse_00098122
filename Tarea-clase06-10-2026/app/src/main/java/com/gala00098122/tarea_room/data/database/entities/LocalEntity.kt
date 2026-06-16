package com.gala00098122.tarea_room.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.gala00098122.tarea_room.data.model.Local

@Entity(
  tableName = "locals",
  foreignKeys = [
    ForeignKey(
      entity = QuestionEntity::class,
      parentColumns = ["id"],
      childColumns = ["questionId"],
      onDelete = ForeignKey.CASCADE
    )
  ],
  indices = [Index("questionId")]
)
data class LocalEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0,
  val name: String,
  val imageUrl: String,
  val votes: Int,
  val questionId: Int
)

fun LocalEntity.toModel(): Local {
  return Local(
    id = id,
    name = name,
    imageUrl = imageUrl,
    votes = votes,
    questionId = questionId
  )
}

fun Local.toEntity(): LocalEntity {
  return LocalEntity(
    id = id,
    name = name,
    imageUrl = imageUrl,
    votes = votes,
    questionId = questionId
  )
}