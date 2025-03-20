package com.example.winterschool.screens.searchscreen

import com.example.domain.models.Books

data class SearchStateScreen(
    val postList: List<Books> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
)