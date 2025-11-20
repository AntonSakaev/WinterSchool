package com.example.data.local.database

import com.example.domain.local.db.BookInfo

object FavoriteMapper {
    fun FavoriteEntity.toDomain(): BookInfo {
        return BookInfo(
            bookId = filmId,
            imageUrl = imageUrl,
            authors = authors,
            bookName = bookName
        )
    }

    fun BookInfo.toEntity(): FavoriteEntity {
        return FavoriteEntity(
            filmId = bookId,
            imageUrl = imageUrl,
            authors = authors,
            bookName = bookName
        )
    }
}