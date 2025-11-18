
package com.example.presentation.screens.searchscreen

import android.util.Log
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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class FavoriteViewModel (
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val checkIsFavoriteUseCase: CheckIsFavoriteUseCase
) : ViewModel() {

    protected val _dBRequestState = MutableStateFlow(BookCardState())
    val dBRequestState = _dBRequestState.asStateFlow()

    protected val _favoriteResults = MutableStateFlow<MutableMap<String?, Boolean?>>(mutableMapOf())
    val favoriteResults = _favoriteResults.asStateFlow()

    fun addFavorite(bookId: String, thumbnail: String, authors: String, title: String) {
        val favorite = Favorite(
            filmId = bookId,
            imageUrl = thumbnail,
            authors = authors,
            bookName = title
        )
        viewModelScope.launch {
            addFavoriteUseCase(favorite).handle(
                onSuccess = { onSuccessRequestToDB(bookId) },
                onError = ::errorRequestToDB,
                onLoading = ::onFavoriteLoading
            )
        }
    }

    fun deleteFavorite(bookId: String) {
        viewModelScope.launch {
            deleteFavoriteUseCase(bookId).handle(
                onSuccess = { onSuccessRequestToDB(bookId) },
                onError = ::errorRequestToDB
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

    fun errorRequestToDB(message: String) {
        _dBRequestState.update { state ->
            state.copy(
                errorMessage = message
            )
        }
    }

    fun onSuccessRequestToDB(bookId: String) {
        checkThisForFavorite(bookId)
        _dBRequestState.update { state ->
            state.copy(
                isLoading = false,
                errorMessage = null
            )
        }
    }

    fun checkThisForFavorite(bookId: String) {
        clearFavorite()
         viewModelScope.launch(Dispatchers.IO) {
            checkIsFavoriteUseCase(bookId).collect {
                it.handle(
                    onLoading = ::onFavoriteLoading,
                    onSuccess = { isFavorite -> successCheckedIsFavorite (bookId, isFavorite)},
                    onError = ::errorRequestToDB
                )
            }
        }
    }

    fun successCheckedIsFavorite(bookId: String, isFavorite: Boolean) {
        _favoriteResults.update {
                currentList ->
            currentList.apply { put(bookId, isFavorite) }
        }
        _dBRequestState.update { state ->
            state.copy(
                isLoading = false,
                isFavorite = isFavorite
            )
        }
    }

    fun clearFavorite() {
        _dBRequestState.update {
            it.copy(
                isLoading = true,
                isFavorite = null,
                errorMessage = null
            )
        }
    }
   }
