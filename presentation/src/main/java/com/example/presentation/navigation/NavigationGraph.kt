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

    val searchViewModel: SearchScreenViewModel = hiltViewModel()
    val detailScreenViewModel: DetailScreenViewModel = hiltViewModel()
    val favoriteScreenViewModel: FavoriteScreenViewModel = hiltViewModel()
    val navigationAction = NavigationAction(navController)

    NavHost(navController, startDestination = Screens.SearchScreen) {

        composable<Screens.SearchScreen> {
            SearchScreen(
                searchViewModel = searchViewModel,
                innerPaddingValues = innerPadding,
                onSettingsClick = { navigationAction.toSettingsScreen() },
                onDetailClick = { navigationAction.toDetailScreen(it) },
                snackbarHostState
            )
        }

        composable<Screens.FavoriteScreen> {
            FavoriteScreen(
                innerPaddingValues = innerPadding,
                favoriteScreenViewModel = favoriteScreenViewModel,
                onExitClick = { navigationAction.backClick()},
                onDetailClick = { navigationAction.toDetailScreen(it) },
                snackbarHostState = snackbarHostState
            )
        }

        composable<Screens.DetailScreen> { backStackEntry ->
            val bookId = backStackEntry.toRoute<Screens.DetailScreen>().selectedBookId
            DetailScreen(
                innerPaddingValues = innerPadding,
                bookId = bookId,
                detailScreenViewModel = detailScreenViewModel,
                onExitClick = { navigationAction.backClick() },
                snackbarHostState
            )

        }

        composable<Screens.SettingsScreen> {
            Settings(innerPadding, searchViewModel) {
                navigationAction.toSearchScreen()
            }
        }
    }
}