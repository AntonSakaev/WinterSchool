package com.example.presentation.screens.searchscreen

import com.example.domain.remote.models.Books
import com.example.presentation.screens.components.screens.ScreenState

data class SearchScreenState (
    val postBooks: Books? = null,
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val isNoKeyWord: Boolean = true
)