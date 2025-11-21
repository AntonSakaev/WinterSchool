package com.example.domain.local.db

data class BookInfo(
    val bookId: String? = null,
    val imageUrl: String? = null,
    val authors: String? = null,
    val bookName: String? = null,
    val publishedDate: String? = null,
    val description: String? = null
)