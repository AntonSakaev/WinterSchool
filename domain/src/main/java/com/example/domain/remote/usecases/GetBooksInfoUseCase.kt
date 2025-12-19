package com.example.domain.remote.usecases

import com.example.domain.remote.BooksRepository
import com.example.domain.remote.models.Books
import com.example.domain.remote.utils.OperationResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBooksInfoUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {
    suspend operator fun invoke(
        request: String, author: String? = null,
        sortByDate: Boolean? = null,
        sortByRelevance: Boolean? = null,
        page: Int = 0
    ): Flow<OperationResult<Books>> {
        val startIndex = page * PAGE_SIZE
        return booksRepository.getBooksInfo(
            request = request,
            author = author,
            sortByDate = sortByDate,
            sortByRelevance = sortByRelevance,
            startIndex = startIndex
        )
    }

    companion object {
        private const val PAGE_SIZE = 5
    }
}