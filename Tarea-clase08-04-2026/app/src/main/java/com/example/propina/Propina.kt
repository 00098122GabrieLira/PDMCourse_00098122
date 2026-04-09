package com.example.propina

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.util.Locale


@Composable
fun PropinaApp(modifier: Modifier = Modifier) {
  var montoIngresado by rememberSaveable { mutableStateOf("") }
  var monto = montoIngresado.toDoubleOrNull()
  var propinaIngresada by rememberSaveable { mutableStateOf("") }
  var propina = propinaIngresada.toDoubleOrNull()
  
  val propinaCalculada = if (monto != null && propina != null) {
    calcularPropina(monto, propina)
  } else 0.00
  
  val total = if (monto != null) {
    monto + propinaCalculada
  } else 0.00
  
  val totalpropina = String.format(Locale.US, "%.2f", propinaCalculada)
  val totalmonto = String.format(Locale.US, "%.2f", total)
  
  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .background(Color.LightGray),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(
      20.dp,
      alignment = Alignment.CenterVertically
    ),
  ) {
    Text(
      text = "Calculadora de propina",
      color = Color.Black,
      fontWeight = FontWeight.Bold
    )
    
    OutlinedTextField(
      value = montoIngresado,
      leadingIcon = {
        Icon(
          painter = painterResource(id = R.drawable.money),
          contentDescription = "",
          tint = Color.Black
        )
      },
      onValueChange = { montoIngresado = it },
      label = { Text("Total gastado") },
      colors = TextFieldDefaults.colors(
        focusedTextColor = Color.Black,
        unfocusedTextColor = Color.Black,
        focusedContainerColor = Color(0xFF87CEEB),
        unfocusedContainerColor = Color(0xFF87CEEB),
        focusedLabelColor = Color.Black,
        unfocusedLabelColor = Color.Black
      ),
      keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Decimal,
        imeAction = ImeAction.Next
      )
    )
    
    OutlinedTextField(
      value = propinaIngresada,
      leadingIcon = {
        Icon(
          painter = painterResource(id = R.drawable.percent),
          contentDescription = "",
          tint = Color.Black
        )
      },
      onValueChange = { propinaIngresada = it },
      label = { Text("Porcentaje de propina") },
      colors = TextFieldDefaults.colors(
        focusedTextColor = Color.Black,
        unfocusedTextColor = Color.Black,
        focusedContainerColor = Color(0xFF87CEEB),
        unfocusedContainerColor = Color(0xFF87CEEB),
        focusedLabelColor = Color.Black,
        unfocusedLabelColor = Color.Black
      ),
      keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Done
      )
    )
    
    Text(
      "Propina: $$totalpropina",
      color = Color.Black
    )
    
    Text(
      "Total a pagar: $$totalmonto",
      color = Color.Black
    )
    
  }
}

fun calcularPropina(monto: Double, propina: Double): Double {
  return monto * (propina / 100)
}