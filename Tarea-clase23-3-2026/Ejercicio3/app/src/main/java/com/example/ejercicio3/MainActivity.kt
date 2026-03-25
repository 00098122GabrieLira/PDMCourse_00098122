package com.example.ejercicio3

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejercicio3.ui.theme.Ejercicio3Theme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      Ejercicio3Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          CuadrantesApp(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}

@Composable
fun CuadrantesApp(modifier: Modifier = Modifier) {
  Column(modifier = Modifier
    .fillMaxSize()) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
    ) {
      Box(
        modifier = Modifier
          .weight(1f)
          .fillMaxHeight()
          .background(Color.LightGray)
          .padding(16.dp),
        contentAlignment = Alignment.Center
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth(),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = stringResource(R.string.Titulo1),
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
          )
          Text(
            text = stringResource(R.string.Descripcion1),
            color = Color.Black,
            textAlign = TextAlign.Justify
          )
        }
      }
      
      Box(
        modifier = Modifier
          .weight(1f)
          .fillMaxHeight()
          .background(Color(0xFF9B6FEC))
          .padding(16.dp),
        contentAlignment = Alignment.Center
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth(),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = stringResource(R.string.Titulo2),
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
          )
          Text(
            text = stringResource(R.string.Descripcion2),
            color = Color.Black,
            textAlign = TextAlign.Justify
          )
        }
      }
    }
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
    ) {
      Box(
        modifier = Modifier
          .weight(1f)
          .fillMaxHeight()
          .background(Color(0xFF9B6FEC))
          .padding(16.dp),
        contentAlignment = Alignment.Center
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth(),
          horizontalAlignment = Alignment.CenterHorizontally,
        ) {
          Text(
            text = stringResource(R.string.Titulo3),
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
          )
          Text(
            text = stringResource(R.string.Descripcion3),
            color = Color.Black,
            textAlign = TextAlign.Justify
          )
        }
      }
      
      Box(
        modifier = Modifier
          .weight(1f)
          .fillMaxHeight()
          .background(Color.LightGray)
          .padding(16.dp),
        contentAlignment = Alignment.Center
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth(),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = stringResource(R.string.Titulo4),
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
          )
          Text(
            text = stringResource(R.string.Descripcion4),
            color = Color.Black,
            textAlign = TextAlign.Justify
          )
        }
      }
    }
  }
}