package com.example.data.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "book_id") val filmId: String? = null,
    @ColumnInfo(name = "image_url") val imageUrl: String?= null,
    @ColumnInfo(name = "authors") val authors: String?= null,
    @ColumnInfo(name = "book_name") val bookName: String?= null
)