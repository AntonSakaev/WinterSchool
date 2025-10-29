
package com.example.presentation.screens.searchscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.local.db.Favorite
import com.example.domain.local.db.usecases.AddFavoriteUseCase
import com.example.domain.local.db.usecases.CheckIsFavoriteUseCase
import com.example.domain.local.db.usecases.DeleteFavoriteUseCase
import com.example.presentation.screens.components.items.BookCardState
import com.example.presentation.screens.utils.handle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class FavoriteViewModel(
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val checkIsFavoriteUseCase: CheckIsFavoriteUseCase
) : ViewModel() {

    protected val _dBRequestState = MutableStateFlow(BookCardState())
    val dBRequestState = _dBRequestState.asStateFlow()

    fun addFavorite(bookId: String, thumbnail: String, authors: String, title: String) {
        val favorite = Favorite(
            filmId = bookId,
            imageUrl = thumbnail,
            authors = authors,
            bookName = title
        )

        viewModelScope.launch {
            addFavoriteUseCase(favorite).handle(
                onSuccess = { onSuccessRequestToDB() },
                onError = ::errorRequestToDB,
                onLoading = ::onFavoriteLoading
            )
        }
    }

    fun deleteFavorite(bookId: String) {
        viewModelScope.launch {
            deleteFavoriteUseCase(bookId).handle(
                onSuccess = { onSuccessRequestToDB() },
                onError = ::errorRequestToDB
            )
        }
    }

    fun clearFavorite() {
        _dBRequestState.update {
            it.copy(
                isLoading = true,
                favoriteResults = mutableListOf(),
                errorMessage = null
            )
        }
    }

    fun onFavoriteLoading() {
        _dBRequestState.update { state ->
            state.copy(
                isLoading = true
            )
        }
    }

    fun successCheckedIsFavorite(isFavorite: Boolean) {
        _dBRequestState.update { state ->
            state.copy(
                isLoading = false,
                favoriteResults = state.favoriteResults.toMutableList()
                    .apply { add(isFavorite) }
            )
        }
    }

    fun errorIdentifyingFavorites(message: String) {
        _dBRequestState.update { state ->
            state.copy(
                isLoading = false,
                favoriteResults = state.favoriteResults.toMutableList()
                    .apply { add(null) },
                errorMessage = message
            )
        }
    }

    fun errorRequestToDB(message: String) {
        _dBRequestState.update { state ->
            state.copy(
                errorMessage = message
            )
        }
    }

    fun onSuccessRequestToDB() {
        _dBRequestState.update { state ->
            state.copy(
                isLoading = false,
                errorMessage = null
            )
        }
    }

    fun checkIsFavorite(bookIds: List<String?>) {
        clearFavorite()
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

    fun checkThisForFavorite(bookId: String) {
        clearFavorite()
        viewModelScope.launch(Dispatchers.IO) {
            checkIsFavoriteUseCase(bookId).collect {
                it.handle(
                    onLoading = ::onFavoriteLoading,
                    onSuccess = ::successCheckedIsFavorite,
                    onError = ::errorRequestToDB
                )
            }
        }
    }
}
