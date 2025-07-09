package com.example.domain.local.prefs

import com.example.domain.local.prefs.models.SearchSettings

interface AppPrefsRepository {
    suspend fun saveSearchSettings(params: SearchSettings)
    suspend fun getSearchSettings(): SearchSettings
}