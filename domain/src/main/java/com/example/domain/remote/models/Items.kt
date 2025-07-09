package com.example.domain.remote.models

data class Items(
    var kind: String? = null,
    var id: String? = null,
    var etag: String? = null,
    var selfLink: String? = null,
    var volumeInfo: VolumeInfo? = null,
    // var saleInfo   : SaleInfo?   = SaleInfo(),
    // var accessInfo : AccessInfo? = AccessInfo(),
    //  var searchInfo : SearchInfo? = SearchInfo()
)