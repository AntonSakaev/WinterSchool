package com.example.domain.usecases

import com.example.domain.BooksRepository
import com.example.domain.models.Books
import com.example.domain.utils.OperationResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBooksInfoUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {
    suspend operator fun invoke(request: String): Flow<OperationResult<Books>> {
        return booksRepository.getBooksInfo(request)
    }
}