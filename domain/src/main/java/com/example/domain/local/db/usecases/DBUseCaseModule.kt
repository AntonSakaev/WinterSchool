package com.example.domain.local.db.usecases

import com.example.domain.local.db.FavoriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBUseCaseModule {
    @Provides
    @Singleton
    fun provideGetFavoritesUseCase(repository: FavoriteRepository): GetFavoritesUseCase {
        return GetFavoritesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAddFavoriteUseCase(repository: FavoriteRepository): AddFavoriteUseCase {
        return AddFavoriteUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteFavoriteUseCase(repository: FavoriteRepository): DeleteFavoriteUseCase {
        return DeleteFavoriteUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideIsFavoriteUseCase(repository: FavoriteRepository): CheckIsFavoriteUseCase {
        return CheckIsFavoriteUseCase(repository)
    }
}