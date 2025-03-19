package com.example.domain

import com.example.domain.models.Items

interface BooksRepository {

    suspend fun getBooksInfo(request: String): List<Items>
}