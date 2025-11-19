package com.example.domain.local.db

import com.example.domain.remote.utils.OperationResult
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun getAllFavorites(): Flow<OperationResult<List<BookInfo>>>
    suspend fun insertFavorite(bookInfo: BookInfo): OperationResult<Unit>
    suspend fun deleteFavoriteById(bookId: String): OperationResult<Unit>
    suspend fun isFavorite(bookId: String): Flow<OperationResult<Boolean>>
}