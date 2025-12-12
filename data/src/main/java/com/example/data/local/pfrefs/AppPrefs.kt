package com.example.data.local.pfrefs

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.local.pfrefs.PreferenceKeys.AUTHOR_NAME
import com.example.data.local.pfrefs.PreferenceKeys.SORT_BY_BEST_MATCH
import com.example.data.local.pfrefs.PreferenceKeys.SORT_BY_DATE
import com.example.data.local.pfrefs.entities.SearchSettingsEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "SearchSettings")

class AppPrefs(context: Context) {
    private val dataStore = context.dataStore

    suspend fun saveSearchSettings(params: SearchSettingsEntity) {
        dataStore.edit { preferences ->
            preferences[AUTHOR_NAME] = params.authorName
            preferences[SORT_BY_DATE] = params.sortByDate
            preferences[SORT_BY_BEST_MATCH] = params.bestMatch
        }
    }

    suspend fun getSearchSettings(): SearchSettingsEntity {
        return dataStore.data.map { preferences ->
            SearchSettingsEntity(
                authorName = preferences[AUTHOR_NAME] ?: "",
                sortByDate = preferences[SORT_BY_DATE] ?: false,
                bestMatch = preferences[SORT_BY_BEST_MATCH] ?: false
            )
        }.first()
    }
}

object PreferenceKeys {
    val AUTHOR_NAME = stringPreferencesKey("author_name")
    val SORT_BY_DATE = booleanPreferencesKey("sort_by_date")
    val SORT_BY_BEST_MATCH = booleanPreferencesKey("best_match")
}