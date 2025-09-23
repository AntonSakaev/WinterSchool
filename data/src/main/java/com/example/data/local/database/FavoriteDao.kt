package com.example.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<FavoriteEntity>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE book_id = :bookId")
    suspend fun deleteFavoriteByFilmId(bookId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE book_id = :bookId)")
    suspend fun isFavorite(bookId: String): Boolean
}