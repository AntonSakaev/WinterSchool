package com.example.presentation.screens.searchscreen

import androidx.lifecycle.viewModelScope
import com.example.domain.local.db.usecases.AddFavoriteUseCase
import com.example.domain.local.db.usecases.CheckIsFavoriteUseCase
import com.example.domain.local.db.usecases.DeleteFavoriteUseCase
import com.example.domain.local.prefs.models.SearchSettings
import com.example.domain.local.prefs.usecases.GetSearchSettings
import com.example.domain.local.prefs.usecases.SaveSearchSettings
import com.example.domain.remote.models.Books
import com.example.domain.remote.usecases.GetBooksInfoUseCase
import com.example.presentation.screens.utils.handle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getBooksInfoUseCase: GetBooksInfoUseCase,
    private val saveSearchSettingsUseCase: SaveSearchSettings,
    private val getSearchSettingsUseCase: GetSearchSettings,
    addFavoriteUseCase: AddFavoriteUseCase,
    deleteFavoriteUseCase: DeleteFavoriteUseCase,
    checkIsFavoriteUseCase: CheckIsFavoriteUseCase
) : FavoriteViewModel(
    addFavoriteUseCase = addFavoriteUseCase,
    deleteFavoriteUseCase = deleteFavoriteUseCase,
    checkIsFavoriteUseCase = checkIsFavoriteUseCase
) {

    private val _uiState = MutableStateFlow(SearchScreenState())
    val uiState = _uiState.asStateFlow()

    private val _keyText = MutableStateFlow("")

    @OptIn(FlowPreview::class)
    private val keyText = _keyText.asStateFlow().debounce(2000)

    private val lastKnowKeyword = MutableStateFlow("")

    private val _searchParams = MutableStateFlow(SearchSettings())
    val searchParams = _searchParams.asStateFlow()

    private val _favoriteResults = MutableStateFlow<MutableList<Boolean?>>(mutableListOf())
    val favoriteResults  = _favoriteResults.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            keyText.collectLatest { keyword ->
                lastKnowKeyword.value = keyword
                launchSearch(keyword)
            }
        }
    }

    fun keywordInput(keyword: String) {
        _keyText.value = keyword
    }

    fun checkIsFavorite(bookIds: List<String?>) {
        clearFavorite()
        _favoriteResults.value.clear()
        viewModelScope.launch(Dispatchers.IO) {
            for (bookId in bookIds) {
                checkIsFavoriteUseCase(bookId ?: "").collect {
                    it.handle(
                        onLoading = ::onFavoriteLoading,
                        onSuccess = ::successCheckedIsFavorite,
                        onError = ::errorIdentifyingFavorites
                    )
                }
            }
        }
    }

    fun successCheckedIsFavorite(isFavorite: Boolean) {
        _favoriteResults.update { currentList ->
            currentList.toMutableList().apply { add(isFavorite) }
        }
        _dBRequestState.update { state ->
            state.copy(
                isLoading = false
            )
        }
    }

    fun errorIdentifyingFavorites(message: String) {
        _favoriteResults.update { currentList ->
            currentList.toMutableList().apply { add(null) }
        }
        _dBRequestState.update { state ->
            state.copy(
                isLoading = false,
                errorMessage = message
            )
        }
    }

    private suspend fun launchSearch(keyword: String) {
        if (keyword.isNotEmpty()) {
            getBooksInfoUseCase(keyword).collect {
                it.handle(
                    onSuccess = ::onSuccess,
                    onLoading = ::onLoading,
                    onError = ::onError
                )
            }
        } else {
            emptyKeyword()
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            launchSearch(lastKnowKeyword.value)
        }
    }

    private fun onError(message: String) {
        _uiState.update { state ->
            state.copy(
                isLoading = false,
                postBooks = null,
                errorMessage = message
            )
        }
    }

    private fun onLoading() {
        _uiState.update { state ->
            state.copy(
                isNoKeyWord = false,
                isLoading = true,
                postBooks = null,
                errorMessage = null
            )
        }
    }

    private fun onSuccess(postBooks: Books) {
        val listIds = postBooks.items.map { it.id }
        if (listIds.isNotEmpty()) {
            checkIsFavorite(listIds)
        }
        _uiState.update { state ->
            state.copy(
                isLoading = false,
                postBooks = postBooks,
                errorMessage = null
            )
        }

    }

    private fun emptyKeyword() {
        _uiState.update { state ->
            state.copy(
                isNoKeyWord = true,
                isLoading = false,
                postBooks = null,
                errorMessage = null
            )
        }
    }

    //region Работа с настройками поиска
    fun saveSearchSettings(
        authorName: String,
        sortByDate: Boolean,
        bestMatch: Boolean
    ) {
        val params = SearchSettings(
            authorName = authorName, sortByDate = sortByDate, bestMatch = bestMatch
        )
        viewModelScope.launch(Dispatchers.IO) {
            saveSearchSettingsUseCase(params)
        }
        getSearchSettings()
    }

    fun getSearchSettings() {
        viewModelScope.launch(Dispatchers.IO) {
            _searchParams.value = getSearchSettingsUseCase()
        }
    }

    private fun updateSearchSettings(update: SearchSettings.() -> SearchSettings) {
        viewModelScope.launch(Dispatchers.IO) {
            val newParams = _searchParams.value.update()
            saveSearchSettingsUseCase(newParams)
            getSearchSettings()
        }
    }

    fun clearAuthorNameFilter() = updateSearchSettings {
        copy(authorName = "")
    }

    fun clearSortByDateFilter() = updateSearchSettings {
        copy(sortByDate = false)
    }

    fun clearBestMatchFilter() = updateSearchSettings {
        copy(bestMatch = false)
    }
    //endregion
}
