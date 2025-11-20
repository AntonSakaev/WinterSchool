package com.example.presentation.screens.booksscreen

import com.example.domain.local.db.BookInfo


data class DetailScreenState(
    val selectedBook: BookInfo? = null,
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)
