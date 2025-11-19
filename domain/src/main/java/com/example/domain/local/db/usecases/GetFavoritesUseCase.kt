package com.example.domain.local.db.usecases

import com.example.domain.local.db.BookInfo
import com.example.domain.local.db.FavoriteRepository
import com.example.domain.remote.utils.OperationResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(): Flow<OperationResult<List<BookInfo>>> {
        return repository.getAllFavorites()
    }
}