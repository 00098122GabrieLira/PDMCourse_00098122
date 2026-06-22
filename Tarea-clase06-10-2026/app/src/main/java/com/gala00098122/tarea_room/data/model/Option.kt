package com.gala00098122.tarea_room.data.model

data class Option(
  val id: Int = 0,
  val value: String,
  val imageUrl: String? = null,
  val votes: Int,
  val questionId: Int = 0
)