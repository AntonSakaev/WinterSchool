package com.example.data.di

import com.example.data.remote.mappers.BooksMapper
import com.example.data.remote.mappers.ImageLinksMapper
import com.example.data.remote.mappers.ItemsMapper
import com.example.data.remote.mappers.VolumeInfoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    fun provideImageLinksMapper(): ImageLinksMapper {
        return ImageLinksMapper()
    }

    @Provides
    fun provideVolumeInfoMapper(imageLinksMapper: ImageLinksMapper): VolumeInfoMapper {
        return VolumeInfoMapper(imageLinksMapper)
    }

    @Provides
    fun provideItemsMapper(volumeInfoMapper: VolumeInfoMapper): ItemsMapper {
        return ItemsMapper(volumeInfoMapper)
    }

    @Provides
    fun provideBooksMapper(itemsMapper: ItemsMapper): BooksMapper {
        return BooksMapper(itemsMapper)
    }
}