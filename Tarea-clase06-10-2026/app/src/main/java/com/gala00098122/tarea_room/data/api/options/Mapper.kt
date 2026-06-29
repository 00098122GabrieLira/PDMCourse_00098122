package com.gala00098122.tarea_room.data.api.options

import com.gala00098122.tarea_room.data.database.entities.OptionEntity

fun OptionsDTO.toEntity(): OptionEntity {
  return OptionEntity(
    id = id,
    value = name,
    imageUrl = imageUrl,
    votes = votes,
    questionId = questionId
  )
}