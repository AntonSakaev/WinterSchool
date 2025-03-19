package com.example.data.remote.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import javax.inject.Inject

@JsonClass(generateAdapter = true)
data class ImageLinksEntity @Inject constructor(
    @Json(name = "smallThumbnail" ) var smallThumbnail : String? = null,
    @Json(name = "thumbnail"      ) var thumbnail      : String? = null
)
