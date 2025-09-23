package com.example.domain.local.db.usecases

import com.example.domain.local.db.Favorite
import com.example.domain.local.db.FavoriteRepository
import com.example.domain.remote.utils.OperationResult
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(favorite: Favorite): OperationResult<Unit> {
        return repository.insertFavorite(favorite)
    }
}