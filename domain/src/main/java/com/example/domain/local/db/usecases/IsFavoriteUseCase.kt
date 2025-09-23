package com.example.domain.local.db.usecases

import com.example.domain.local.db.FavoriteRepository
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(bookId: String): Boolean {
        return repository.isFavorite(bookId)
    }
}