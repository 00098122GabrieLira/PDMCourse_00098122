package com.gala00098122.tarea_room.data.remote.locals.getLocals

import com.gala00098122.tarea_room.data.model.Local

fun LocalDTO.toModel(): Local {
  return Local(
    id = id,
    name = name,
    imageUrl = imageUrl,
    votes = votes
  )
}