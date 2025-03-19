package com.example.data.remote.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import javax.inject.Inject

@JsonClass(generateAdapter = true)
data class ItemsEntity @Inject constructor(
    @Json(name = "kind") var kind: String? = null,
    @Json(name = "id") var id: String? = null,
    @Json(name = "etag") var etag: String? = null,
    @Json(name = "selfLink") var selfLink: String? = null,
    @Json(name = "volumeInfo") var volumeInfo: VolumeInfoEntity? = null,
    // @Json("saleInfo"   ) var saleInfo   : SaleInfo?   = SaleInfo(),
    // @Json("accessInfo" ) var accessInfo : AccessInfo? = AccessInfo(),
    // @Json("searchInfo" ) var searchInfo : SearchInfo? = SearchInfo()
)