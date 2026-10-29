package com.example.domain.remote.models


data class Books    (
    val kind: String? = null,
    val totalItems: Int? = null,
    val items: List<Items> = listOf()
)
