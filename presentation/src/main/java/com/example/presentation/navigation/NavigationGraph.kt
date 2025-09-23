package com.example.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.presentation.screens.booksscreen.DetailScreen
import com.example.presentation.screens.booksscreen.DetailScreenViewModel
import com.example.presentation.screens.searchscreen.SearchScreen
import com.example.presentation.screens.searchscreen.SearchScreenViewModel
import com.example.presentation.screens.searchscreen.Settings

@Composable
fun NavigationGraph(
    navController: NavHostController,
    innerPadding: PaddingValues
) {

    val searchViewModel: SearchScreenViewModel = hiltViewModel()
    val detailScreenViewModel: DetailScreenViewModel = hiltViewModel()
    val navigationAction = NavigationAction(navController)

    NavHost(navController, startDestination = Screens.SearchScreen) {

        composable<Screens.SearchScreen> {
            SearchScreen(
                searchViewModel = searchViewModel,
                innerPaddingValues = innerPadding,
                onSettingsClick = { navigationAction.toSettingsScreen() },
                onDetailClick = { navigationAction.toDetailScreen(it) }
            )
        }

        composable<Screens.FavoriteScreen> {

        }

        composable<Screens.DetailScreen> { backStackEntry ->
            val bookId = backStackEntry.toRoute<Screens.DetailScreen>().selectedBookId
            DetailScreen(
                innerPaddingValues = innerPadding,
                bookId = bookId,
                detailScreenViewModel = detailScreenViewModel,
                onExitClick = { navigationAction.backClick() }
            )

        }

        composable<Screens.SettingsScreen> {
            Settings(innerPadding, searchViewModel) {
                navigationAction.toSearchScreen()
            }
        }
    }
}