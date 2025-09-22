package com.example.presentation.screens.booksscreen

import com.example.domain.remote.models.Items

data class DetailScreenState(
val selectedBook: Items? = null,
val isLoading: Boolean = true,
val errorMessage: String? = null,
)
