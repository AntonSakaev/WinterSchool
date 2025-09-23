package com.example.domain.local.db.usecases

import com.example.domain.local.db.FavoriteRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(filmId: String) {
        repository.deleteFavoriteById(filmId)
    }
}