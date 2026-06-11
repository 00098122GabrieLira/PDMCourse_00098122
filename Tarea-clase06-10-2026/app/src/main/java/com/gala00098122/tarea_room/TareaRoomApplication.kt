package com.gala00098122.tarea_room

import android.app.Application
import com.gala00098122.tarea_room.data.AppProvider

class TareaRoomApplication : Application() {
  val appProvider by lazy { AppProvider(this) }
}