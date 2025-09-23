package com.example.domain.local.db

import com.example.domain.remote.utils.OperationResult

interface FavoriteRepository {
    suspend fun getAllFavorites(): OperationResult<List<Favorite>>
    suspend fun insertFavorite(favorite: Favorite): OperationResult<Unit>
    suspend fun deleteFavoriteById(bookId: String): OperationResult<Unit>
    suspend fun isFavorite(bookId: String): OperationResult<Boolean>
}