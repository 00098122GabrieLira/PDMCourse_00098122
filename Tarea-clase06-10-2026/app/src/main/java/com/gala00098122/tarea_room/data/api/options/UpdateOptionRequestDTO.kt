package com.gala00098122.tarea_room.data.api.options

import kotlinx.serialization.Serializable

@Serializable
data class UpdateOptionRequestDTO (
val name:String
)

@Serializable
data class UpdateOptionResponseDTO (
  val id: Int,
  val name: String,
  val imageUrl: String,
  val questionId: Int,
  val votes: Int
)