package com.gala00098122.tarea_room.navegation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.gala00098122.tarea_room.screens.home.LocalsScreen
import com.gala00098122.tarea_room.navegation.routes.Routes
import com.gala00098122.tarea_room.screens.questions.QuestionScreen

@Composable
fun RoomNavigation() {
  val backStack = rememberNavBackStack(Routes.Home)
  
  NavDisplay(
    backStack = backStack,
    onBack = { backStack.removeLastOrNull() },
    entryProvider = entryProvider {
      entry<Routes.Home> {
        QuestionScreen(
          navigateToLocals = { questionId ->
            backStack.add(Routes.Locals(questionId))
          }
        )
        
      }
      entry<Routes.Locals> {
        LocalsScreen(
          questionId = it.questionId,
          navigateBack = { backStack.removeLastOrNull()},
        )
      }
    },
    transitionSpec = {
      slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(500)
      ) togetherWith slideOutHorizontally(
        targetOffsetX = { -it },
        animationSpec = tween(500)
      )
    },
    popTransitionSpec = {
      slideInHorizontally(
        initialOffsetX = { -it },
        animationSpec = tween(500)
      ) togetherWith slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(500)
      )
    },
    predictivePopTransitionSpec = {
      slideInHorizontally(
        initialOffsetX = { -it },
        animationSpec = tween(250)
      ) togetherWith slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(250)
      )
    }
  )
}