package com.example.data.remote

import com.example.data.remote.entities.BooksEntity
import com.example.data.remote.entities.ItemsEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksAPI {

    @GET("volumes")
    suspend fun getBooksInfo(
        @Query("q") bookTitle: String,
        @Query("orderBy") orderBy: String? = null,
        @Query("startIndex") startIndex: Int = 0,
        @Query("maxResults") maxResults: Int = 5
    ): Response<BooksEntity>

    @GET("volumes/{selectedBookId}")
    suspend fun getSelectedBookInfo(
        @Path("selectedBookId") bookId: String
    ): Response<ItemsEntity>
}