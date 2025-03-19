package com.example.domain

import com.example.domain.models.Books
import com.example.domain.utils.OperationResult

interface BooksRepository {

    suspend fun getBooksInfo(request: String): OperationResult<Books>

}