package com.example.data.di

import com.example.domain.local.db.FavoriteRepository
import com.example.domain.local.db.usecases.AddFavoriteUseCase
import com.example.domain.local.db.usecases.CheckIsFavoriteUseCase
import com.example.domain.local.db.usecases.DeleteFavoriteUseCase
import com.example.domain.local.db.usecases.GetFavoritesUseCase
import com.example.domain.local.prefs.AppPrefsRepository
import com.example.domain.local.prefs.usecases.GetSearchSettings
import com.example.domain.local.prefs.usecases.SaveSearchSettings
import com.example.domain.remote.BooksRepository
import com.example.domain.remote.usecases.GetBooksInfoUseCase
import com.example.domain.remote.usecases.GetSelectedBookUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

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
    fun provideCheckIsFavoriteUseCase(repository: FavoriteRepository): CheckIsFavoriteUseCase {
        return CheckIsFavoriteUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetSearchSettingsUseCase(repository: AppPrefsRepository): GetSearchSettings {
        return GetSearchSettings(repository)
    }

    @Provides
    @Singleton
    fun provideSaveSearchSettingsUseCase(repository: AppPrefsRepository): SaveSearchSettings {
        return SaveSearchSettings(repository)
    }

    @Provides
    @Singleton
    fun provideGetBooksInfoUseCase(repository: BooksRepository): GetBooksInfoUseCase {
        return GetBooksInfoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetSelectedBookUseCase(repository: BooksRepository): GetSelectedBookUseCase {
        return GetSelectedBookUseCase(repository)
    }
}
