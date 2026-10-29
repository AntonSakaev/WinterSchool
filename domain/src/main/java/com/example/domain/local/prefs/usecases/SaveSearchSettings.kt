package com.example.domain.local.prefs.usecases

import com.example.domain.local.prefs.AppPrefsRepository
import com.example.domain.local.prefs.models.SearchSettings

class SaveSearchSettings(
    private val appPrefsRepository: AppPrefsRepository
) {
    suspend operator fun invoke(params: SearchSettings) {
        appPrefsRepository.saveSearchSettings(params)
    }
}
