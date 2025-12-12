package com.example.data.local.database

import com.example.data.local.database.FavoriteMapper.toDomain
import com.example.data.local.database.FavoriteMapper.toEntity
import com.example.domain.local.db.BookInfo
import com.example.domain.local.db.FavoriteRepository
import com.example.domain.remote.utils.OperationResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
) : DatabaseDataSource(), FavoriteRepository {

    override suspend fun getAllFavorites(): Flow<OperationResult<List<BookInfo>>> {
        return safeFlowDatabaseOperation {
            favoriteDao.getAllFavorites().map { entities -> entities.map { it.toDomain() } }
        }
    }

    override suspend fun insertFavorite(bookInfo: BookInfo): OperationResult<Unit> {
        return safeDatabaseOperation {
            favoriteDao.insertFavorite(bookInfo.toEntity())
        }
    }

    override suspend fun deleteFavoriteById(bookId: String): OperationResult<Unit> {
        return safeDatabaseOperation {
            favoriteDao.deleteFavoriteByFilmId(bookId)
        }
    }

    override suspend fun isFavorite(bookId: String): Flow<OperationResult<Boolean>> {
        return safeFlowDatabaseOperation {
                favoriteDao.isFavorite(bookId)
            }
    }
}


