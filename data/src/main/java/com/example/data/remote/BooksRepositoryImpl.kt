package com.example.data.remote

import com.example.data.remote.mappers.BooksMapper
import com.example.domain.BooksRepository
import com.example.domain.models.Books
import com.example.domain.utils.OperationResult
import com.example.domain.utils.RemoteDataSource
import com.example.domain.utils.flatMapIfSuccess
import com.example.domain.utils.toSuccessResult
import javax.inject.Inject

class BooksRepositoryImpl
@Inject constructor(
    private val booksAPI: BooksAPI,
    private val booksMapper: BooksMapper
) : BooksRepository, RemoteDataSource() {

    override suspend fun getBooksInfo(request: String): OperationResult<Books> {
        return safeApiCall {  booksAPI.getBooksInfo(request)}.flatMapIfSuccess { booksEntity ->
          booksMapper(booksEntity).toSuccessResult()
        }
    }
}
