package com.example.domain.remote.usecases

import com.example.domain.local.db.BookInfo
import com.example.domain.remote.BooksRepository
import com.example.domain.remote.models.Items
import com.example.domain.remote.utils.OperationResult
import com.example.domain.remote.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSelectedBookUseCase(
    private val booksRepository: BooksRepository
) {
    suspend operator fun invoke(selectedBookId: String): Flow<OperationResult<BookInfo>> {
        return booksRepository.getSelectedBook(selectedBookId)
            .map { operationResult ->
                operationResult.map { item ->
                    item.toBookInfo()
                }
            }
    }

    private fun Items.toBookInfo(): BookInfo {
        return BookInfo(
            bookId = this.id ?: "",
            imageUrl = this.volumeInfo?.imageLinks?.thumbnail ?: "",
            authors = this.volumeInfo?.authors?.joinToString() ?: "",
            bookName = this.volumeInfo?.title ?: "",
            publishedDate = this.volumeInfo?.publishedDate,
            description = this.volumeInfo?.description
        )
    }
}
