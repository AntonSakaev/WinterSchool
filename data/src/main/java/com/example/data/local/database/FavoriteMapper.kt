package com.example.data.local.database

import com.example.domain.local.db.Favorite

object FavoriteMapper {
    fun FavoriteEntity.toDomain(): Favorite {
        return Favorite(
            filmId = filmId,
            imageUrl = imageUrl,
            authors = authors,
            bookName = bookName
        )
    }

    fun Favorite.toEntity(): FavoriteEntity {
        return FavoriteEntity(
            filmId = filmId,
            imageUrl = imageUrl,
            authors = authors,
            bookName = bookName
        )
    }
}