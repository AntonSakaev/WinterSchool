package com.example.domain.local.db.usecases

import com.example.domain.local.db.FavoriteRepository
import com.example.domain.remote.utils.OperationResult

class DeleteFavoriteUseCase(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(bookId: String): OperationResult<Unit> {
        return repository.deleteFavoriteById(bookId)
    }
}
