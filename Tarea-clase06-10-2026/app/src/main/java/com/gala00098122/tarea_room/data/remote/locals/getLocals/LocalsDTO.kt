package com.gala00098122.tarea_room.data.remote.locals.getLocals

import kotlinx.serialization.Serializable

@Serializable
data class LocalDTO(
  val id: Int,
  val name: String,
  val imageUrl: String,
  val votes: Int
)