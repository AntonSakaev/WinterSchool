package com.example.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screens () {
    @Serializable
    data object SearchScreen : Screens()

    @Serializable
    data object FavoriteScreen: Screens()

    @Serializable
    data class DetailScreen (val bookId: Int) :Screens()
}