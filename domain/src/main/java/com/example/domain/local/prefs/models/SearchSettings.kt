package com.example.domain.local.prefs.models

import javax.inject.Inject

data class SearchSettings @Inject constructor(
    val authorName: String = "",
    val changedName: Boolean = false,
    val sortByDate: Boolean = false,
    val bestMatch: Boolean = false
) {
    fun isEnabled(): Boolean {
        return authorName != "" || changedName||sortByDate || bestMatch
    }
}