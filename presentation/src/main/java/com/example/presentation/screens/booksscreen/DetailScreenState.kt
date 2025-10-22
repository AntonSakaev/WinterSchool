package com.example.presentation.screens.booksscreen

import com.example.domain.remote.models.Items


data class DetailScreenState(
    val selectedBook: Items? = null,
    val isLoading: Boolean = true,
    val isLoadingFromDB: Boolean = false,
    val isFavorite: Boolean? = null,
    val errorMessage: String? = null,
)
