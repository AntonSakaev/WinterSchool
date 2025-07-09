package com.example.data.local.pfrefs

import com.example.data.local.pfrefs.mappers.ToSearchSettingsEntityMapper
import com.example.data.local.pfrefs.mappers.ToSearchSettingsMapper
import com.example.domain.local.prefs.AppPrefsRepository
import com.example.domain.local.prefs.models.SearchSettings
import javax.inject.Inject

class AppPrefsImpl @Inject constructor(
    private val appPreferences: AppPrefs,
    private val toSearchSettingsMapper: ToSearchSettingsMapper,
    private val toSearchSettingsEntityMapper: ToSearchSettingsEntityMapper
): AppPrefsRepository {

    override suspend fun saveSearchSettings(params: SearchSettings) {
        appPreferences.saveSearchSettings(toSearchSettingsEntityMapper(params))
    }

    override suspend fun  getSearchSettings(): SearchSettings {
        return toSearchSettingsMapper(appPreferences.getSearchSettings())
    }
}