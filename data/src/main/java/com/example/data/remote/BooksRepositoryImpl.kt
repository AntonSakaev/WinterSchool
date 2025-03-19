package com.example.data.remote

import com.example.data.remote.mappers.ItemsMapper
import com.example.domain.BooksRepository
import com.example.domain.models.Items
import javax.inject.Inject

class BooksRepositoryImpl
@Inject constructor(
    private val booksAPI: BooksAPI,
    private val itemsMapper: ItemsMapper
): BooksRepository{
    override suspend fun getBooksInfo(request: String): List<Items> {
       return booksAPI.getBooksInfo(request).items.map { itemsMapper(it) }
    }
}