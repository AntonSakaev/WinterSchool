package com.example.domain.local.db.usecases

import com.example.domain.local.db.FavoriteRepository
import com.example.domain.remote.utils.OperationResult
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(bookId: String): OperationResult<Boolean> {
        return repository.isFavorite(bookId)
    }
}