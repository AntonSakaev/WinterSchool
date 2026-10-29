package com.example.presentation.navigation

import androidx.navigation.NavController

class NavigationAction(private val navController: NavController) {

    fun toSettingsScreen() {
        navController.navigate(Screens.SettingsScreen)
    }

    fun toSearchScreen() {
        navController.navigate(Screens.SearchScreen) {
            popUpTo(Screens.SearchScreen) { inclusive = false }
        }
    }

    fun toDetailScreen(selectedBookId: String) {
        navController.navigate(Screens.DetailScreen(selectedBookId))
    }

    fun backClick() {
        navController.popBackStack()
    }
}
