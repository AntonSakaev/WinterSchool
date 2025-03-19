package com.example.data.remote.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import javax.inject.Inject

@JsonClass(generateAdapter = true)
data class BooksEntity
    @Inject constructor(
    @Json(name = "kind"       ) var kind       : String?          = null,
    @Json(name = "totalItems" ) var totalItems : Int?             = null,
    @Json(name = "items"      ) var items      : List<ItemsEntity> = listOf()
)
