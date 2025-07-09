package com.example.domain.remote.models

import androidx.compose.runtime.Stable


@Stable
data class Books    (
    var kind: String? = null,
    var totalItems: Int? = null,
    var items: List<Items> = listOf()
)
