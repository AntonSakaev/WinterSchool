package com.example.data.remote.mappers

import com.example.data.remote.entities.ImageLinksEntity
import com.example.domain.models.ImageLinks
import javax.inject.Inject

class ImageLinksMapper @Inject constructor() : (ImageLinksEntity) -> ImageLinks {
    override fun invoke(from: ImageLinksEntity): ImageLinks =
        ImageLinks(
            smallThumbnail = from.smallThumbnail,
            thumbnail = from.thumbnail
        )
}