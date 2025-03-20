package com.example.winterschool.screens.searchscreen

import com.example.domain.models.Books

data class SearchScreenState (
    val postBooks: Books? = null,
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
)