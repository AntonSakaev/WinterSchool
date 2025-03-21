package com.example.data.remote.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemsEntity (
    @Json(name = "kind") var kind: String? = null,
    @Json(name = "id") var id: String? = null,
    @Json(name = "etag") var etag: String? = null,
    @Json(name = "selfLink") var selfLink: String? = null,
    @Json(name = "volumeInfo") var volumeInfo: VolumeInfoEntity? = null,
    // @Json("saleInfo"   ) var saleInfo   : SaleInfo?   = SaleInfo(),
    // @Json("accessInfo" ) var accessInfo : AccessInfo? = AccessInfo(),
    // @Json("searchInfo" ) var searchInfo : SearchInfo? = SearchInfo()
)