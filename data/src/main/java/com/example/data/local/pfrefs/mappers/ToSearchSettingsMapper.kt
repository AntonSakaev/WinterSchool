package com.example.data.local.pfrefs.mappers

import com.example.data.local.pfrefs.entities.SearchSettingsEntity
import com.example.domain.local.prefs.models.SearchSettings
import javax.inject.Inject

class ToSearchSettingsMapper @Inject constructor() : (SearchSettingsEntity) -> SearchSettings {
    override fun invoke(from: SearchSettingsEntity): SearchSettings =
        SearchSettings(
            authorName = from.authorName,
            sortByDate = from.sortByDate,
            bestMatch = from.bestMatch
        )
}