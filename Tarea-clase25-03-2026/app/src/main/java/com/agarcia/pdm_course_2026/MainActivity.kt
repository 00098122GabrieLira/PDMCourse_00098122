package com.agarcia.pdm_course_2026

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.agarcia.pdm_course_2026.clase_250326.LemonadeApp
import com.agarcia.pdm_course_2026.ui.theme.PDM_Course_2026Theme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      PDM_Course_2026Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          LemonadeApp(
            modifier = Modifier.padding(innerPadding),
            )
        }
      }
    }
  }
}
