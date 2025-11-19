package com.example.data.local.database

import com.example.data.local.database.FavoriteMapper.toDomain
import com.example.data.local.database.FavoriteMapper.toEntity
import com.example.data.remote.RemoteDataSource
import com.example.domain.local.db.BookInfo
import com.example.domain.local.db.FavoriteRepository
import com.example.domain.remote.utils.OperationResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
) : RemoteDataSource(), FavoriteRepository {

    override suspend fun getAllFavorites(): Flow<OperationResult<List<BookInfo>>> {
        return flow {
            emit(OperationResult.Loading)
            val responseResult = safeApiCall {
            Response.success(favoriteDao.getAllFavorites().map { it.toDomain() })
        }
        emit(responseResult)}
    }

    override suspend fun insertFavorite(bookInfo: BookInfo): OperationResult<Unit> {
        return safeApiCall {
            Response.success(favoriteDao.insertFavorite(bookInfo.toEntity()))
        }
    }

    override suspend fun deleteFavoriteById(bookId: String): OperationResult<Unit> {
        return safeApiCall {
            Response.success(favoriteDao.deleteFavoriteByFilmId(bookId))
        }
    }

    override suspend fun isFavorite(bookId: String): Flow<OperationResult<Boolean>> {
        return flow {
            emit(OperationResult.Loading)
            val responseResult = safeApiCall {
            Response.success(favoriteDao.isFavorite(bookId))
        }
            emit(responseResult)
    }}
}


