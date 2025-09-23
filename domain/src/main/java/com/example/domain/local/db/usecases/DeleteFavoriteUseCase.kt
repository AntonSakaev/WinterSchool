package com.example.domain.local.db.usecases

import com.example.domain.local.db.FavoriteRepository
import com.example.domain.remote.utils.OperationResult
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(filmId: String): OperationResult<Unit> {
        return repository.deleteFavoriteById(filmId)
    }
}