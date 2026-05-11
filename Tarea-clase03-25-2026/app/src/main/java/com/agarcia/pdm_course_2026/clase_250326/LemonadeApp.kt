package com.agarcia.pdm_course_2026.clase_250326

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agarcia.pdm_course_2026.R
import kotlin.random.Random

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
  var currentStep by rememberSaveable { mutableIntStateOf(1) }
  var tapsNeeded by rememberSaveable { mutableIntStateOf(Random.nextInt(2, 5)) }
  var currentTaps by rememberSaveable { mutableIntStateOf(0) }
  
  val imageResource = when (currentStep) {
    1 -> R.drawable.lemon_tree
    2 -> R.drawable.lemon_squeeze
    3 -> R.drawable.lemon_drink
    else -> R.drawable.lemon_restart
  }
  
  val buttonText = when (currentStep) {
    1 -> stringResource(R.string.texto_img_1)
    2 -> stringResource(R.string.texto_img_2)
    3 -> stringResource(R.string.texto_img_3)
    else -> stringResource(R.string.texto_img_4)
  }
  
  val onImageClick: () -> Unit = {
    if (currentStep == 2) {
      currentTaps++
      if (currentTaps >= tapsNeeded) {
        currentStep++
        currentTaps = 0
      }
    } else if (currentStep == 4) {
      tapsNeeded = Random.nextInt(2, 5)
      currentStep = 1
    } else {
      currentStep++
    }
  }
  
  Column(
    modifier = modifier
      .fillMaxSize()
      .background(Color.White)
  ) {
    TopBar()
    LemonadeContainer(
      imageResource,
      buttonText,
      onClickImage = onImageClick,
      currentTaps,
      tapsNeeded
    )
  }
}

@Composable
fun TopBar() {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .background(Color.Yellow)
      .height(50.dp)
  ) {
    Text(
      text = "Limonada App",
      color = Color.Black,
      fontSize = 18.sp,
      fontWeight = FontWeight.Bold,
      modifier = Modifier.align(Alignment.Center)
    )
  }
}

@Composable
fun LemonadeContainer(
  imageResource: Int,
  text: String,
  onClickImage: () -> Unit,
  currentTaps: Int,
  taps: Int
) {
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Button(
      onClick = onClickImage,
      shape = RoundedCornerShape(24.dp),
      colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC3ECD2))
    ) {
      Image(
        painter = painterResource(id = imageResource),
        contentDescription = text,
      )
    }
    Spacer(Modifier.height(24.dp))
    Text(
      text = text,
      color = Color.Black
    )
    if (imageResource == R.drawable.lemon_squeeze) {
      Text(
        text = "Número de pasos faltantes:  ${taps - currentTaps}",
        color = Color.Black
      )
    }
  }
}
