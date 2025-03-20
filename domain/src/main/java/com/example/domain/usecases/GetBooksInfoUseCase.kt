package com.example.domain.usecases

import com.example.domain.BooksRepository
import com.example.domain.models.Books
import com.example.domain.utils.OperationResult
import javax.inject.Inject

class GetBooksInfoUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {
    suspend operator fun invoke (request: String): OperationResult<Books>{

       return booksRepository.getBooksInfo(request)
    }
}