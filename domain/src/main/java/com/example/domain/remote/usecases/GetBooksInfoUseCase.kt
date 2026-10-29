package com.example.domain.remote.usecases

import com.example.domain.remote.BooksRepository
import com.example.domain.remote.models.Books
import com.example.domain.remote.utils.OperationResult
import kotlinx.coroutines.flow.Flow

class GetBooksInfoUseCase(
    private val booksRepository: BooksRepository
) {
    suspend operator fun invoke(
        request: String,
        author: String? = null,
        sortByDate: Boolean? = null,
        sortByRelevance: Boolean? = null,
        page: Int = 0
    ): Flow<OperationResult<Books>> {
        return booksRepository.getBooksInfo(
            request = request,
            author = author,
            sortByDate = sortByDate,
            sortByRelevance = sortByRelevance,
            startIndex = page
        )
    }
}
