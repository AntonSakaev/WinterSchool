package com.example.winterschool.navigation

import kotlinx.serialization.Serializable

sealed class Screens () {
    @Serializable
    data object SearchScreen :Screens()
}