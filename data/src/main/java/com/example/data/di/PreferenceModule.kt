package com.example.data.di

import android.content.Context
import com.example.data.local.pfrefs.AppPrefs
import com.example.data.local.pfrefs.AppPrefsImpl
import com.example.data.local.pfrefs.mappers.ToSearchSettingsEntityMapper
import com.example.data.local.pfrefs.mappers.ToSearchSettingsMapper
import com.example.domain.local.prefs.AppPrefsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

    @Provides
    @Singleton
    fun provideAppPrefs(@ApplicationContext context: Context): AppPrefs {
        return AppPrefs(context)
    }

    @Provides
    @Singleton
    fun provideAppPrefsRepository(
        appPreferences: AppPrefs,
        toSearchSettingsMapper: ToSearchSettingsMapper,
        toSearchSettingsEntityMapper: ToSearchSettingsEntityMapper
    ): AppPrefsRepository {
        return AppPrefsImpl(appPreferences, toSearchSettingsMapper, toSearchSettingsEntityMapper)
    }
}