package com.example.ejercicio1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejercicio1.ui.theme.Ejercicio1Theme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      Ejercicio1Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          JetpackApp(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}

@Composable
fun JetpackApp(modifier: Modifier = Modifier) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.White)
  ) {
    Image(
      painter = painterResource(R.drawable.bg_compose_background),
      contentDescription = "Fondo Jetpack",
      modifier = Modifier.fillMaxWidth()
    )
    Text(
      text = stringResource(R.string.Texto1),
      color = Color.Black,
      fontSize = 24.sp,
      modifier = Modifier.padding(16.dp),
      textAlign = TextAlign.Center
    )
    Text(
      text = stringResource(R.string.Texto2),
      color = Color.Black,
      modifier = Modifier.padding(start = 16.dp, end = 16.dp),
      textAlign = TextAlign.Justify
    )
    Text(
      text = stringResource(R.string.Texto3),
      color = Color.Black,
      modifier = Modifier.padding(16.dp),
      textAlign = TextAlign.Justify
    )
  }
}
