package com.example.data.remote.mappers

import com.example.data.remote.entities.ImageLinksEntity
import com.example.data.remote.entities.VolumeInfoEntity
import com.example.domain.remote.models.ImageLinks
import com.example.domain.remote.models.VolumeInfo
import javax.inject.Inject

class VolumeInfoMapper @Inject constructor(
    private val imageLinksMapper: (ImageLinksEntity) -> ImageLinks
) : (VolumeInfoEntity) -> VolumeInfo {
    override operator fun invoke(from: VolumeInfoEntity): VolumeInfo =
        VolumeInfo(
            title = from.title,
            authors = from.authors,
            publisher = from.publisher,
            publishedDate = from.publishedDate,
            description = from.description,
            pageCount = from.pageCount,
            printType = from.printType,
            categories = from.categories,
            maturityRating = from.maturityRating,
            allowAnonLogging = from.allowAnonLogging,
            contentVersion = from.contentVersion,
            imageLinks = from.imageLinks?.let { imageLinksMapper(it) },
            language = from.language,
            previewLink = from.previewLink,
            infoLink = from.infoLink,
            canonicalVolumeLink = from.canonicalVolumeLink
        )
}
