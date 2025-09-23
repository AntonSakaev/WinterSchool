package com.example.data.local.database

import com.example.data.local.database.FavoriteMapper.toDomain
import com.example.data.local.database.FavoriteMapper.toEntity
import com.example.data.remote.RemoteDataSource
import com.example.domain.local.db.Favorite
import com.example.domain.local.db.FavoriteRepository
import com.example.domain.remote.utils.OperationResult
import retrofit2.Response
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
) : RemoteDataSource(), FavoriteRepository {

    override suspend fun getAllFavorites(): OperationResult<List<Favorite>> {
        return safeApiCall {
            Response.success(favoriteDao.getAllFavorites().map { it.toDomain() })
        }
    }

    override suspend fun insertFavorite(favorite: Favorite): OperationResult<Unit> {
        return safeApiCall {
            Response.success(favoriteDao.insertFavorite(favorite.toEntity()))
        }
    }

    override suspend fun deleteFavoriteById(bookId: String): OperationResult<Unit> {
        return safeApiCall {
            Response.success(favoriteDao.deleteFavoriteByFilmId(bookId))
        }
    }

    override suspend fun isFavorite(bookId: String): OperationResult<Boolean> {
        return safeApiCall {
            Response.success(favoriteDao.isFavorite(bookId))
        }
    }
}