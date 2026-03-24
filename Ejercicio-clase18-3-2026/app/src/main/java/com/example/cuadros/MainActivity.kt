package com.example.cuadros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.cuadros.ui.theme.CuadrosTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      CuadrosTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          CuadrosApp(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}

@Composable
fun CuadrosApp(modifier: Modifier = Modifier) {
  Column(
    modifier = modifier.fillMaxSize()
  ) {
    // Ejemplo 1 -> Cuadro celeste texto al centro
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
        .background(Color.Cyan),
      contentAlignment = Alignment.Center
    ) {
      Text(
        text = "Ejemplo 1",
        fontSize = 14.sp,
        color = Color.Black
      )
    }
    
    Row(
      modifier = Modifier
        .fillMaxWidth().weight(1f)
    ){
      // Ejemplo 2 -> cuadro rojo texto al centro
      Box(
        modifier = Modifier
          .weight(1f)
          .fillMaxHeight()
          .background(Color.Red),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = "Ejemplo 2",
          fontSize = 14.sp,
          color = Color.Black
        )
      }
      
      // Ejemplo 3 -> cuadro verde texto en esquina superior izquierda
      Box(
        modifier = Modifier
          .weight(1f)
          .fillMaxHeight()
          .background(Color.Green),
        contentAlignment = Alignment.TopStart
      ) {
        Text(
          text = "Ejemplo 3",
          fontSize = 14.sp,
          color = Color.Black
        )
      }
    }
    
    // Ejemplo 4 -> Cuadro rosado texto abajo en el centro
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
        .background(Color.Magenta),
      contentAlignment = Alignment.BottomCenter
    ) {
      Text(
        text = "Ejemplo 4",
        fontSize = 14.sp,
        color = Color.Black
      )
    }
  }
}
