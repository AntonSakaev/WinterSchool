package com.example.data.local.database

import com.example.data.local.database.FavoriteMapper.toDomain
import com.example.data.local.database.FavoriteMapper.toEntity
import com.example.domain.local.db.BookInfo
import com.example.domain.local.db.FavoriteRepository
import com.example.domain.remote.utils.OperationResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao,
    private val dataSource: DatabaseDataSource
) : FavoriteRepository {

    override fun getAllFavorites(): Flow<OperationResult<List<BookInfo>>> {
        return dataSource.safeFlowDatabaseOperation {
            favoriteDao.getAllFavorites().map { entities -> entities.map { it.toDomain() } }
        }
    }

    override suspend fun insertFavorite(bookInfo: BookInfo): OperationResult<Unit> {
        return dataSource.safeDatabaseOperation {
            favoriteDao.insertFavorite(bookInfo.toEntity())
        }
    }

    override suspend fun deleteFavoriteById(bookId: String): OperationResult<Unit> {
        return dataSource.safeDatabaseOperation {
            favoriteDao.deleteFavoriteById(bookId)
        }
    }

    override fun isFavorite(bookId: String): Flow<OperationResult<Boolean>> {
        return dataSource.safeFlowDatabaseOperation {
            flowOf(favoriteDao.isFavorite(bookId))
        }
    }
}
