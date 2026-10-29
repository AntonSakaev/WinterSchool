package com.example.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.presentation.screens.booksscreen.DetailScreen
import com.example.presentation.screens.booksscreen.DetailScreenViewModel
import com.example.presentation.screens.favoritescreen.FavoriteScreen
import com.example.presentation.screens.favoritescreen.FavoriteScreenViewModel
import com.example.presentation.screens.searchscreen.SearchScreen
import com.example.presentation.screens.searchscreen.SearchScreenViewModel
import com.example.presentation.screens.searchscreen.Settings

@Composable
fun NavigationGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    snackbarHostState: SnackbarHostState
) {

    NavHost(
        navController = navController,
        startDestination = Screens.SearchScreen
    ) {

        composable<Screens.SearchScreen> {
            val viewModel: SearchScreenViewModel = hiltViewModel()
            SearchScreen(
                searchViewModel = viewModel,
                innerPaddingValues = innerPadding,
                onSettingsClick = { navController.navigate(Screens.SettingsScreen) },
                onDetailClick = { navController.navigate(Screens.DetailScreen(it)) },
                snackbarHostState
            )
        }

        composable<Screens.FavoriteScreen> {
            val viewModel: FavoriteScreenViewModel = hiltViewModel()
            FavoriteScreen(
                innerPaddingValues = innerPadding,
                favoriteScreenViewModel = viewModel,
                onExitClick = { navController.popBackStack() },
                onDetailClick = { navController.navigate(Screens.DetailScreen(it)) },
                snackbarHostState = snackbarHostState
            )
        }

        composable<Screens.DetailScreen> { backStackEntry ->
            val bookId = backStackEntry.toRoute<Screens.DetailScreen>().selectedBookId
            val viewModel: DetailScreenViewModel = hiltViewModel()
            DetailScreen(
                innerPaddingValues = innerPadding,
                bookId = bookId,
                detailScreenViewModel = viewModel,
                onExitClick = { navController.popBackStack() },
                snackbarHostState
            )
        }

        composable<Screens.SettingsScreen> {
            val viewModel: SearchScreenViewModel = hiltViewModel()
            Settings(innerPadding, viewModel) {
                navController.popBackStack(Screens.SearchScreen::class, false)
            }
        }
    }
}
