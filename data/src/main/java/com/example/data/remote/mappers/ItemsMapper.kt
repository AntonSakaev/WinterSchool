package com.example.data.remote.mappers

import com.example.data.remote.entities.ItemsEntity
import com.example.data.remote.entities.VolumeInfoEntity
import com.example.domain.remote.models.Items
import com.example.domain.remote.models.VolumeInfo
import javax.inject.Inject


class ItemsMapper @Inject constructor(
    private val volumeInfoMapper: (VolumeInfoEntity) -> VolumeInfo
) : (ItemsEntity) -> Items {
    override operator fun invoke(from: ItemsEntity): Items =
        Items(
            kind = from.kind,
            id = from.id,
            etag = from.etag,
            selfLink = from.selfLink,
            volumeInfo = from.volumeInfo?.let { volumeInfoMapper(it) }
        )
}


