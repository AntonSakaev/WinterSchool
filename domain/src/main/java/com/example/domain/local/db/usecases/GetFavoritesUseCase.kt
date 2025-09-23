package com.example.domain.local.db.usecases

import com.example.domain.local.db.Favorite
import com.example.domain.local.db.FavoriteRepository
import com.example.domain.remote.utils.OperationResult
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(): OperationResult<List<Favorite>> {
        return repository.getAllFavorites()
    }
}