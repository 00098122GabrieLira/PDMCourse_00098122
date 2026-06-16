package com.gala00098122.tarea_room.data.model

data class Local(
  val id: Int = 0,
  val name: String,
  val imageUrl: String,
  val votes: Int,
  val questionId: Int = 0
)