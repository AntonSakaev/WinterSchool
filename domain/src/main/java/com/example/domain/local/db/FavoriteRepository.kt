package com.example.domain.local.db

interface FavoriteRepository {
    suspend fun getAllFavorites(): List<Favorite>
    suspend fun insertFavorite(favorite: Favorite)
    suspend fun deleteFavoriteById(bookId: String)
    suspend fun isFavorite(bookId: String): Boolean
}