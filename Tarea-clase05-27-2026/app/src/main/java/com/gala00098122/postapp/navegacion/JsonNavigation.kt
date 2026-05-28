package com.gala00098122.postapp.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.gala00098122.postapp.ui.screens.jsonListScreen.JsonListScreen
import com.gala00098122.postapp.ui.screens.postJsonScreen.JsonPostScreen

@Composable
fun JsonNavigation(
  modifier: Modifier = Modifier
) {
  val backStack = rememberNavBackStack(Routes.Home)
  
  NavDisplay(
    backStack = backStack,
    onBack = { backStack.removeLastOrNull() },
    entryProvider = entryProvider {
      entry<Routes.Home> {
        JsonListScreen(
          navigateToCreate = { backStack.add(Routes.Create) }
        )
      }
      entry<Routes.Create> {
        JsonPostScreen(
          navigateBack = { backStack.removeLastOrNull() }
        )
      }
    }
  )
}