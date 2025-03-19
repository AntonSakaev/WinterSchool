package com.example.data.remote.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import javax.inject.Inject

@JsonClass(generateAdapter = true)
data class VolumeInfoEntity @Inject constructor(
    @Json(name = "title") var title: String? = null,
    @Json(name = "authors") var authors: List<String> = listOf(),
    @Json(name = "publisher") var publisher: String? = null,
    @Json(name = "publishedDate") var publishedDate: String? = null,
    @Json(name = "description") var description: String? = null,
   // @Json(name = "industryIdentifiers") var industryIdentifiers: ArrayList<IndustryIdentifiers> = arrayListOf(),
   // @Json(name = "readingModes") var readingModes: ReadingModes? = ReadingModes(),
    @Json(name = "pageCount") var pageCount: Int? = null,
    @Json(name = "printType") var printType: String? = null,
    @Json(name = "categories") var categories: List<String> = listOf(),
    @Json(name = "maturityRating") var maturityRating: String? = null,
    @Json(name = "allowAnonLogging") var allowAnonLogging: Boolean? = null,
    @Json(name = "contentVersion") var contentVersion: String? = null,
  //  @Json(name = "panelizationSummary") var panelizationSummary: PanelizationSummary? = PanelizationSummary(),
    @Json(name = "imageLinks") var imageLinks: ImageLinksEntity? = null,
    @Json(name = "language") var language: String? = null,
    @Json(name = "previewLink") var previewLink: String? = null,
    @Json(name = "infoLink") var infoLink: String? = null,
    @Json(name = "canonicalVolumeLink") var canonicalVolumeLink: String? = null
)