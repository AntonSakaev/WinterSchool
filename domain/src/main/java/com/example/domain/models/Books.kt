package com.example.domain.models

import androidx.compose.runtime.Stable


@Stable
data class Books    (
    var kind: String? = null,
    var totalItems: Int? = null,
    var items: List<Items> = listOf()
)
