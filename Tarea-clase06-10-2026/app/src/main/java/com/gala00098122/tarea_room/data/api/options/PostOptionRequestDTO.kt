package com.gala00098122.tarea_room.data.api.options

import kotlinx.serialization.Serializable

@Serializable
data class PostOptionRequestDTO(
  val name: String,
  val imageUrl: String,
  val questionId: Int
)

@Serializable
data class PostOptionResponseDTO(
  val id: Int,
  val name: String,
  val imageUrl: String,
  val questionId: Int,
  val votes: Int
)