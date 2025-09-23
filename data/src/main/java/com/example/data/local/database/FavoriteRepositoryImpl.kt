package com.example.data.local.database

import com.example.data.local.database.FavoriteMapper.toDomain
import com.example.data.local.database.FavoriteMapper.toEntity
import com.example.domain.local.db.Favorite
import com.example.domain.local.db.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoriteRepository {

    override suspend fun getAllFavorites(): List<Favorite> {
        return favoriteDao.getAllFavorites().map { it.toDomain() }
    }

    override suspend fun insertFavorite(favorite: Favorite) {
        favoriteDao.insertFavorite(favorite.toEntity())
    }

    override suspend fun deleteFavoriteById(bookId: String) {
        favoriteDao.deleteFavoriteByFilmId(bookId)
    }

    override suspend fun isFavorite(bookId: String): Boolean {
        return favoriteDao.isFavorite(bookId)
    }
}