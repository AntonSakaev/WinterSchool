package com.example.presentation.screens.booksscreen

import com.example.domain.remote.models.Items
import com.example.presentation.screens.components.screens.ScreenState

data class DetailScreenState(
    val selectedBook: Items? = null,
    override val isLoading: Boolean = true,
    override val errorMessage: String? = null,
) : ScreenState
