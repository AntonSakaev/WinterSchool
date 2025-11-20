package com.example.domain.local.db

data class BookInfo(
    val bookId: String,
    val imageUrl: String,
    val authors: String,
    val bookName: String,
    val publishedDate: String? = null,
    val description: String? = null
)