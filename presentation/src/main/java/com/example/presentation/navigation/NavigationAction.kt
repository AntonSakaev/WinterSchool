package com.example.presentation.navigation

import androidx.navigation.NavController

class NavigationAction (private val navController: NavController){

    fun toSettingsScreen (){
        navController.navigate(
            route = Screens.SettingsScreen
        )
    }

    fun toSearchScreen (){
        navController.navigate(
            route = Screens.SearchScreen
        ){popUpTo<Screens.SettingsScreen> { inclusive=true }}
    }
}