package com.example.domain.remote.usecases

import android.util.Log
import com.example.domain.remote.BooksRepository
import com.example.domain.remote.models.Items
import com.example.domain.remote.utils.OperationResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSelectedBookUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {
    suspend operator fun invoke(selectedBookId: String): Flow<OperationResult<Items>> {
        Log.d("TAG", "invoke: ")
        return booksRepository.getSelectedBook(selectedBookId)
    }
}