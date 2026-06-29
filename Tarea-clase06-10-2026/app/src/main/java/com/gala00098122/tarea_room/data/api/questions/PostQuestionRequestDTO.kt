package com.gala00098122.tarea_room.data.api.questions

import kotlinx.serialization.Serializable

@Serializable
data class PostQuestionRequestDTO (
  val title:String
)

@Serializable
data class PostQuestionResponseDTO (
  val id: Int,
  val title:String
)
