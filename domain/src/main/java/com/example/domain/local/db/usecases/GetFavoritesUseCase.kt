package com.example.domain.local.db.usecases

import com.example.domain.local.db.Favorite
import com.example.domain.local.db.FavoriteRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(): List<Favorite> {
        return repository.getAllFavorites()
    }
}