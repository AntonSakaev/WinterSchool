package com.example.domain.models

data class Books    (
    var kind: String? = null,
    var totalItems: Int? = null,
    var items: List<Items> = listOf()
)
