package com.example.domain

import com.example.domain.models.Books
import com.example.domain.utils.OperationResult
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    suspend fun getBooksInfo(request: String): Flow<OperationResult<Books>>

}