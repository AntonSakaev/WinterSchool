package com.example.domain.local.db

import com.example.domain.remote.utils.OperationResult
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun getAllFavorites(): OperationResult<List<Favorite>>
    suspend fun insertFavorite(favorite: Favorite): OperationResult<Unit>
    suspend fun deleteFavoriteById(bookId: String): OperationResult<Unit>
    suspend fun isFavorite(bookId: String): Flow<OperationResult<Boolean>>
}