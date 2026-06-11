package com.gala00098122.tarea_room.data.remote.locals.getLocals

import kotlinx.serialization.Serializable

@Serializable
data class VoteLocalRequestDTO(
  val optionId: Int,
)

@Serializable
data class VotedLocalResponseDTO(
  val ok: Boolean,
  val message: String
)