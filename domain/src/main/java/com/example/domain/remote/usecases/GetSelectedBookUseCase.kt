package com.example.domain.remote.usecases

import com.example.domain.local.db.BookInfo
import com.example.domain.remote.BooksRepository
import com.example.domain.remote.utils.OperationResult
import com.example.domain.remote.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSelectedBookUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {
    suspend operator fun invoke(selectedBookId: String): Flow<OperationResult<BookInfo>> {
        return booksRepository.getSelectedBook(selectedBookId)
            .map { operationResult ->
                operationResult.map { item ->
                    BookInfo(
                        bookId = item.id ?: "",
                        imageUrl = item.volumeInfo?.imageLinks?.thumbnail ?: "",
                        authors = item.volumeInfo?.authors?.joinToString() ?: "",
                        bookName = item.volumeInfo?.title ?: "",
                        publishedDate = item.volumeInfo?.publishedDate,
                        description = item.volumeInfo?.description
                    )
                }
            }
    }
}