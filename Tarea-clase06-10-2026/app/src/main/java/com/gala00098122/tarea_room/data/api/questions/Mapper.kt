package com.gala00098122.tarea_room.data.api.questions

import com.gala00098122.tarea_room.data.database.entities.QuestionEntity

fun QuestionDTO.toEntity(): QuestionEntity {
  return QuestionEntity(
    id = id,
    title = title
  )
}