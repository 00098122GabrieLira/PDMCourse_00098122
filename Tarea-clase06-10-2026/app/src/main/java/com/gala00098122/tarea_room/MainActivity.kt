package com.gala00098122.tarea_room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.gala00098122.tarea_room.navegation.RoomNavigation
import com.gala00098122.tarea_room.ui.theme.TareaRoomTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      TareaRoomTheme {
        RoomNavigation()
      }
    }
  }
}
