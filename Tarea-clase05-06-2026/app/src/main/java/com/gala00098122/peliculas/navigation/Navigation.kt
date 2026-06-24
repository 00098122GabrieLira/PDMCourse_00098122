package com.gala00098122.peliculas.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.gala00098122.peliculas.ui.screens.favoriteMovieScreen.FavoriteMovieScreen
import com.gala00098122.peliculas.ui.screens.movieList.MovieListScreen
import com.gala00098122.peliculas.ui.screens.movieDetailScreen.MovieDetailScreen
import com.gala00098122.peliculas.ui.screens.movieDetailScreenV2.MovieDetailScreenV2
import com.gala00098122.peliculas.ui.screens.searchScreen.SearchScreen
import com.gala00098122.peliculas.ui.screens.upComingScreen.UpComingScreen
import com.gala00098122.peliculas.ui.screens.versions.VersionScreen

@Composable
fun MovieApp() {
  val backStack = rememberNavBackStack(Routes.Home)
  
  NavDisplay(
    backStack = backStack,
    onBack = { backStack.removeLastOrNull() },
    entryProvider = entryProvider {
      entry<Routes.Home> {
        MovieListScreen(
          navigateToVersions = { movieId ->
            backStack.add(Routes.Versions(movieId))
          },
          navigateToUpComing = { backStack.add(Routes.Soon) },
          navigateToSearch = { backStack.add(Routes.Search) },
          navigateToFavorites = { backStack.add(Routes.Favorite) }
        )
      }
      entry<Routes.Versions> { key ->
        VersionScreen(
          movieId = key.movieId,
          navigateBack = {
            backStack.removeLastOrNull()
          },
          navigateToDetail1 = { movieId ->
            backStack.add(Routes.MovieDetail1(movieId))
          },
          navigateToDetail2 = { movieId ->
            backStack.add(Routes.MovieDetail2(movieId))
          },
        )
      }
      entry<Routes.MovieDetail1> { key ->
        MovieDetailScreen(
          movieId = key.movieId,
          navigateBack = {
            backStack.removeLastOrNull()
          }
        )
      }
      entry<Routes.MovieDetail2> { key ->
        MovieDetailScreenV2(
          movieId = key.movieId,
          navigateBack = {
            backStack.removeLastOrNull()
          }
        )
      }
      entry<Routes.Soon> {
        UpComingScreen(
          navigateBack = {
            backStack.removeLastOrNull()
          },
          navigateToVersions = { movieId ->
            backStack.add(Routes.Versions(movieId))
          }
        )
      }
      entry<Routes.Search> {
        SearchScreen(
          navigateBack = {
            backStack.removeLastOrNull()
          },
          navigateToVersions = { movieId ->
            backStack.add(Routes.Versions(movieId))
          }
        )
      }
      entry<Routes.Favorite> {
        FavoriteMovieScreen(
          navigateBack = {
            backStack.removeLastOrNull()
          },
          navigateToVersions = { movieId ->
            backStack.add(Routes.Versions(movieId))
          }
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