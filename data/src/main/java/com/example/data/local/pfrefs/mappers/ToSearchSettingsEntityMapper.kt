package com.example.data.local.pfrefs.mappers

import com.example.data.local.pfrefs.entities.SearchSettingsEntity
import com.example.domain.local.prefs.models.SearchSettings
import javax.inject.Inject

class ToSearchSettingsEntityMapper @Inject constructor() : (SearchSettings) -> SearchSettingsEntity {
    override fun invoke(from: SearchSettings): SearchSettingsEntity =
        SearchSettingsEntity(
            authorName = from.authorName,
            sortByDate = from.sortByDate,
            bestMatch = from.bestMatch
        )
}
