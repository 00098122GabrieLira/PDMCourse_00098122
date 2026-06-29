package com.gala00098122.tarea_room.data.api.questions

import kotlinx.serialization.Serializable

@Serializable
data class UpdateQuestionRequestDTO (
  val id:Int,
  val title:String
)

@Serializable
data class UpdateQuestionResponseDTO (
  val id: Int,
  val title:String
)
