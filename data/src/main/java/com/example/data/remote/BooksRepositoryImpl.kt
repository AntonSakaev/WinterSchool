package com.example.data.remote

import com.example.data.remote.mappers.BooksMapper
import com.example.domain.BooksRepository
import com.example.domain.models.Books
import com.example.domain.utils.OperationResult
import com.example.domain.utils.flatMapIfSuccess
import com.example.domain.utils.toSuccessResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BooksRepositoryImpl
@Inject constructor(
    private val booksAPI: BooksAPI,
    private val booksMapper: BooksMapper
) : BooksRepository, RemoteDataSource() {

    override suspend fun getBooksInfo(request: String): Flow<OperationResult<Books>> {
        return flow {emit(OperationResult.Loading)
           val responseResult = safeApiCall { booksAPI.getBooksInfo(request) }.flatMapIfSuccess { booksEntity ->
                booksMapper(booksEntity).toSuccessResult()
            }
            emit(responseResult)
        }
    }
}
