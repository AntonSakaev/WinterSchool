package com.example.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.presentation.screens.searchscreen.SearchScreen
import com.example.presentation.screens.searchscreen.SearchScreenViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    innerPadding: PaddingValues
) {

    val searchViewModel: SearchScreenViewModel = hiltViewModel()

    NavHost(navController, startDestination = Screens.SearchScreen) {
        composable<Screens.SearchScreen> {
            SearchScreen(
                searchViewModel = searchViewModel,
                innerPaddingValues = innerPadding
            )
        }

        composable<Screens.FavoriteScreen> {

        }

        composable<Screens.DetailScreen> {

        }
    }
}