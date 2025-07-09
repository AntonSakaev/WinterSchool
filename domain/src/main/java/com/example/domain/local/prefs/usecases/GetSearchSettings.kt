package com.example.domain.local.prefs.usecases

import com.example.domain.local.prefs.AppPrefsRepository
import com.example.domain.local.prefs.models.SearchSettings
import javax.inject.Inject

class GetSearchSettings @Inject constructor(
    private val appPrefsRepository: AppPrefsRepository
) {
    suspend operator fun invoke (): SearchSettings {
        return appPrefsRepository.getSearchSettings()
    }
}