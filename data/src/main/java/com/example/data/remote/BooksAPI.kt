package com.example.data.remote

import com.example.data.remote.entities.BooksEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksAPI {

    @GET("volumes")
    suspend fun getBooksInfo(
        @Query("q") bookTitle: String
    ): Response<BooksEntity>
}