package com.example.domain.local.db.usecases

import com.example.domain.local.db.BookInfo
import com.example.domain.local.db.FavoriteRepository
import com.example.domain.remote.utils.OperationResult
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(bookInfo: BookInfo): OperationResult<Unit> {
        return repository.insertFavorite(bookInfo)
    }
}