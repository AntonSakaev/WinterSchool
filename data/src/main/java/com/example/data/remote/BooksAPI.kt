package com.example.data.remote

import com.example.data.remote.entities.BooksEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface BooksAPI {

    @GET("volumes?q={request}")
    suspend fun getBooksInfo(
        @Path("request") request: String
    ): BooksEntity
}