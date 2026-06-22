package com.gala00098122.tarea_room.data.remote.locals.getLocals

import com.gala00098122.tarea_room.data.model.Option

fun LocalDTO.toModel(): Option {
  return Option(
    id = id,
    value = name,
    imageUrl = imageUrl,
    votes = votes
  )
}