package com.example.data.remote

import com.example.data.remote.mappers.BooksMapper
import com.example.data.remote.mappers.ItemsMapper
import com.example.domain.remote.BooksRepository
import com.example.domain.remote.models.Books
import com.example.domain.remote.models.Items
import com.example.domain.remote.utils.OperationResult
import com.example.domain.remote.utils.flatMapIfSuccess
import com.example.domain.remote.utils.toSuccessResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BooksRepositoryImpl
@Inject constructor(
    private val booksAPI: BooksAPI,
    private val booksMapper: BooksMapper,
    private val itemsMapper: ItemsMapper
) : BooksRepository, RemoteDataSource() {

    override suspend fun getBooksInfo(
        request: String,
        author: String?,
        sortByDate: Boolean?,
        sortByRelevance: Boolean?,
        startIndex: Int
    ): Flow<OperationResult<Books>> {
        return flow {
            emit(OperationResult.Loading)
            val searchQuery = buildQuery(request, author)
            val orderBy = buildOrderBy(sortByDate, sortByRelevance)
            val responseResult = safeApiCall {
                booksAPI.getBooksInfo(
                    bookTitle = searchQuery,
                    orderBy = orderBy,
                    startIndex = startIndex
                )
            }.flatMapIfSuccess { booksEntity ->
                booksMapper(booksEntity).toSuccessResult()
            }
            emit(responseResult)
        }
    }

    override suspend fun getSelectedBook(selectedBookId: String): Flow<OperationResult<Items>> {
        return flow {
            emit(OperationResult.Loading)
            val responseResult =
                safeApiCall { booksAPI.getSelectedBookInfo(selectedBookId) }.flatMapIfSuccess { itemsEntity ->
                    itemsMapper(itemsEntity).toSuccessResult()
                }
            emit(responseResult)
        }
    }

    private fun buildQuery(searchQuery: String, author: String?): String {
        return  if (!author.isNullOrBlank()) {
            "$searchQuery inauthor:$author"
        } else {
            searchQuery
        }
    }

    private fun buildOrderBy(
        sortByDate: Boolean?,
        sortByRelevance: Boolean?
    ): String? {
        return when {
            sortByDate == true -> "newest"
            sortByRelevance == true -> "relevance"
            else -> null
        }
    }
}
