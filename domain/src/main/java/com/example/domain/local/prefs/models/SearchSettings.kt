package com.example.domain.local.prefs.models

data class SearchSettings(
    val authorName: String = "",
    val sortByDate: Boolean = false,
    val bestMatch: Boolean = false
) {
    fun isEnabled(): Boolean {
        return authorName != "" || sortByDate || bestMatch
    }
}
