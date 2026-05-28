package com.gala00098122.postapp.data.api.getJson

import kotlinx.serialization.Serializable

@Serializable
data class CreatePostRequestDTO(
  val userId: Int,
  val title: String,
  val body: String
)