package com.example.domain.local.db.usecases

import com.example.domain.local.db.FavoriteRepository
import com.example.domain.remote.utils.OperationResult
import kotlinx.coroutines.flow.Flow

class CheckIsFavoriteUseCase(
    private val repository: FavoriteRepository
) {
    operator fun invoke(bookId: String): Flow<OperationResult<Boolean>> {
        return repository.isFavorite(bookId)
    }
}
