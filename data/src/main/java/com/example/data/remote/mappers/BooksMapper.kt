package com.example.data.remote.mappers

import com.example.data.remote.entities.BooksEntity
import com.example.data.remote.entities.ItemsEntity
import com.example.domain.models.Books
import com.example.domain.models.Items
import javax.inject.Inject

class BooksMapper @Inject constructor(
    private val itemsMapper: (ItemsEntity) -> Items
) : (BooksEntity) -> Books {
    override fun invoke(from: BooksEntity): Books =
        Books(
            kind = from.kind,
            totalItems = from.totalItems,
            items = from.items.map { itemsMapper(it) }
        )
}