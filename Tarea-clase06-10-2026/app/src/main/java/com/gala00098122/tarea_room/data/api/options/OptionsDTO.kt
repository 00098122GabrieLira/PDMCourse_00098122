package com.gala00098122.tarea_room.data.api.options

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OptionsDTO(
  val id: Int,
  val name: String,
  val imageUrl: String,
  val votes:Int,
  val questionId: Int
)