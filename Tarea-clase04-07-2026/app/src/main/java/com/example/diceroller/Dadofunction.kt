package com.example.diceroller

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
  var resultado by rememberSaveable { mutableIntStateOf(1) }
  
  val imageResource = when (resultado) {
    1 -> R.drawable.dice_1
    2 -> R.drawable.dice_2
    3 -> R.drawable.dice_3
    4 -> R.drawable.dice_4
    5 -> R.drawable.dice_5
    else -> R.drawable.dice_6
  }
  
  Column(
    modifier = modifier
      .fillMaxSize()
      .background(Color.White),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Image(
      painter = painterResource(imageResource),
      contentDescription = "1"
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(
      onClick = { resultado = (1..6).random() },
      colors = ButtonDefaults.buttonColors(
        containerColor = Color(0xFF000000),
        contentColor = Color.White
      )
    ) {
      Text(
        text = stringResource(R.string.texto_boton)
      )
    }
  }
}

@Preview
@Composable
fun DiceRollerApp(modifier: Modifier = Modifier) {
  DiceWithButtonAndImage(
    modifier = modifier
      .fillMaxSize()
      .wrapContentSize(Alignment.Center)
  )
}
