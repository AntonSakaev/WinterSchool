package com.example.data.di

import android.content.Context
import com.example.data.local.database.AppDatabase
import com.example.data.local.database.FavoriteDao
import com.example.data.local.database.FavoriteRepositoryImpl
import com.example.domain.local.db.FavoriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideFavoriteDao(appDatabase: AppDatabase): FavoriteDao {
        return appDatabase.favoriteDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(favoriteDao: FavoriteDao): FavoriteRepository {
        return FavoriteRepositoryImpl(favoriteDao)
    }
}