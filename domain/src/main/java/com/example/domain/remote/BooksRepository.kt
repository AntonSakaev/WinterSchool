package com.example.domain.remote

import com.example.domain.remote.models.Books
import com.example.domain.remote.models.Items
import com.example.domain.remote.utils.OperationResult
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    suspend fun getBooksInfo(
        request: String,
        author: String?,
        sortByDate: Boolean?,
        sortByRelevance: Boolean?,
        startIndex: Int
    ): Flow<OperationResult<Books>>

    suspend fun getSelectedBook(selectedBookId: String): Flow<OperationResult<Items>>
}