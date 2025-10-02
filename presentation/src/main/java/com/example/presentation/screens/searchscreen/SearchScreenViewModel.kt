package com.example.presentation.screens.searchscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.local.db.Favorite
import com.example.domain.local.db.usecases.AddFavoriteUseCase
import com.example.domain.local.db.usecases.DeleteFavoriteUseCase
import com.example.domain.local.db.usecases.IsFavoriteUseCase
import com.example.domain.local.prefs.models.SearchSettings
import com.example.domain.remote.models.Books
import com.example.domain.local.prefs.usecases.GetSearchSettings
import com.example.domain.local.prefs.usecases.SaveSearchSettings
import com.example.domain.remote.usecases.GetBooksInfoUseCase
import com.example.presentation.screens.components.items.BookCardState
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.presentation.screens.utils.handle
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
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchScreenState())
    val uiState = _uiState.asStateFlow()

    private val _keyText = MutableStateFlow("")

    private val lastKnowKeyword = MutableStateFlow("")

    @OptIn(FlowPreview::class)
    private val keyText = _keyText.asStateFlow().debounce(2000)

    private val _searchParams = MutableStateFlow(SearchSettings())
    val searchParams = _searchParams.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            keyText.collectLatest { keyword ->
                lastKnowKeyword.value = keyword
                launchSearch(keyword)
            }
        }
    }

    private val _isFavorite = MutableStateFlow(BookCardState())
    val isFavorite = _isFavorite.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()


    fun addFavorite(bookId: String, thumbnail: String, authors: String, title: String) {
        val favorite = Favorite(
            filmId = bookId,
            imageUrl = thumbnail,
            authors = authors,
            bookName = title
        )
        viewModelScope.launch {
            addFavoriteUseCase(favorite).handle(
                onSuccess = {
                    _error.value = null
                },
                onError = { message ->
                    _error.value = "Ошибка добавления в избранное: $message"
                }
            )
        }
    }

    fun deleteFavorite(bookId: String) {
        viewModelScope.launch {
            deleteFavoriteUseCase(bookId).handle(
                onSuccess = {

                    _error.value = null
                },
                onError = { message ->
                    _error.value = "Ошибка удаления из избранного: $message"
                }
            )
        }
    }

    fun checkIsFavorite(bookIds: List<String?>) {
        Log.d("STARTFUN", "checkIsFavorite: ")
        _isFavorite.update {
            it.copy(
                isLoading = true,
                favoriteResults = mutableListOf(),
                errorMessage = null
            )
        }

        viewModelScope.launch(Dispatchers.IO) {
            for (bookId in bookIds) {
                isFavoriteUseCase(bookId ?: "").collect {
                    it.handle(
                        onLoading = {
                            _isFavorite.update { state ->
                                state.copy(
                                    isLoading = true
                                )
                            }
                        },
                        onSuccess = { isFavorit ->
                            _isFavorite.update { state ->
                                state.copy(
                                    isLoading = false,
                                    favoriteResults = state.favoriteResults.toMutableList()
                                        .apply { add(isFavorit) }
                                )
                            }
                            Log.d(
                                "VIEWMODEL",
                                "checkIsFavorite: ${isFavorite.value.favoriteResults}"
                            )

                        },
                        onError = { message ->
                            _isFavorite.update { state ->
                                state.copy(
                                    isLoading = false,
                                    favoriteResults = state.favoriteResults.toMutableList()
                                        .apply { add(null) },
                                    errorMessage = "Ошибка для $bookId: $message"
                                )
                            }
                        }
                    )
                }
            }
        }
    }
//    fun checkIsFavorite(bookIds: List<String>) {
//
//        viewModelScope.launch (Dispatchers.IO){
//            val results = mutableListOf<Boolean>()
//            for (bookId in bookIds){
//            isFavoriteUseCase(bookId)
//                .collect {
//                it.handle(
//                onSuccess = { isFavorit ->
//
//                    _isFavorite.update { state ->
//                        state.copy(
//                            isLoading = false,
//                            isFavorite = isFavorit,
//                            errorMessage = null
//                        )
//                    }
//                },
//                onLoading = {
//                    _isFavorite.update { state ->
//                    state.copy(
//                        isLoading = true,
//                        isFavorite = false,
//                        errorMessage = null
//                    )
//
//                }
//                            },
//                onError = { message ->
//                    _isFavorite.update { state ->
//                        state.copy(
//                            isLoading = false,
//                            isFavorite = null,
//                            errorMessage = "Ошибка проверки избранного: $message"
//                        )
//                    }
//                }
//            ) }
//        }}
//    }

    fun clearError() {
        _error.value = null
    }

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

    fun keywordInput(keyword: String) {
        _keyText.value = keyword
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

    //region Обработка состояний загрузки из сети
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
        _uiState.update { state ->
            state.copy(
                isLoading = false,
                postBooks = postBooks,
                errorMessage = null
            )
        }
        val listIds = postBooks.items.map { it.id }
        if (listIds.isNotEmpty()) {
            checkIsFavorite(listIds)
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
    //endregion
}