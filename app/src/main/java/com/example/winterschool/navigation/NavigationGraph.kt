package com.example.winterschool.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.winterschool.screens.searchscreen.SearchScreen

@Composable
fun NavigationGraph(navController: NavHostController) {


    NavHost(navController, startDestination = Screens.SearchScreen) {
        composable<Screens.SearchScreen> {
            SearchScreen()
        }
    }
}