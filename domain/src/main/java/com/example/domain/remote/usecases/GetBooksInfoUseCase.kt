package com.example.domain.remote.usecases

import com.example.domain.remote.BooksRepository
import com.example.domain.remote.models.Books
import com.example.domain.remote.utils.OperationResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBooksInfoUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {
    suspend operator fun invoke(request: String): Flow<OperationResult<Books>> {
        return booksRepository.getBooksInfo(request)
    }
}