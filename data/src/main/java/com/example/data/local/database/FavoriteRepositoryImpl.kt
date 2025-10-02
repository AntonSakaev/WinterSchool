package com.example.data.local.database

import com.example.data.local.database.FavoriteMapper.toDomain
import com.example.data.local.database.FavoriteMapper.toEntity
import com.example.data.remote.RemoteDataSource
import com.example.domain.local.db.Favorite
import com.example.domain.local.db.FavoriteRepository
import com.example.domain.remote.models.Books
import com.example.domain.remote.utils.OperationResult
import com.example.domain.remote.utils.flatMapIfSuccess
import com.example.domain.remote.utils.toSuccessResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    override suspend fun isFavorite(bookId: String): Flow<OperationResult<Boolean>> {
        return flow {
            emit(OperationResult.Loading)
            val responseResult = safeApiCall {
            Response.success(favoriteDao.isFavorite(bookId))
        }
            emit(responseResult)
    }}
}


