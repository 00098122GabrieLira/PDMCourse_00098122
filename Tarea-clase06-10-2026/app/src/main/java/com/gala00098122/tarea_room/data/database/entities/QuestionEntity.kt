package com.gala00098122.tarea_room.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gala00098122.tarea_room.data.model.Question

@Entity(tableName = "questions")
data class QuestionEntity(
  @PrimaryKey(autoGenerate = false)
  val id: Int,
  val title: String,
)

fun QuestionEntity.toModel(): Question {
  return Question(
    id = id,
    title = title
  )
}

fun Question.toEntity(): QuestionEntity {
  return QuestionEntity(
    id = id,
    title = title,
  )
}