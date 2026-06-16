package com.gala00098122.tarea_room.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.gala00098122.tarea_room.data.model.Question

data class QuestionWithOptions(
  @Embedded val question: QuestionEntity,
  @Relation(
    parentColumn = "id",
    entityColumn = "questionId"
  )
  val options: List<LocalEntity>
)

fun QuestionWithOptions.toModel(): Question {
  return Question(
    id = question.id,
    title = question.title,
    optionCount = options.size,
  )
}