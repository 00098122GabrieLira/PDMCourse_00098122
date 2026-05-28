package com.gala00098122.postapp.data.api.getJson

import com.gala00098122.postapp.model.Json

fun JsonDTO.toModel(): Json {
  return Json(
    userId = userId,
    id = id,
    title = title,
    body = body
  )
}