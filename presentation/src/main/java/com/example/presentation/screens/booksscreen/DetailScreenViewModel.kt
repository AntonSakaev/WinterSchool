package com.example.presentation.screens.booksscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.local.db.Favorite
import com.example.domain.local.db.usecases.AddFavoriteUseCase
import com.example.domain.local.db.usecases.DeleteFavoriteUseCase
import com.example.domain.local.db.usecases.GetFavoritesUseCase
import com.example.domain.local.db.usecases.CheckIsFavoriteUseCase
import com.example.domain.remote.models.Items
import com.example.domain.remote.usecases.GetSelectedBookUseCase
import com.example.presentation.screens.components.items.BookCardState
import com.example.presentation.screens.utils.handle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val getSelectedBookUseCase: GetSelectedBookUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val checkIsFavoriteUseCase: CheckIsFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailScreenState())
    val uiState = _uiState.asStateFlow()

    private var isInitialized = false

    fun getSelectedBook(keyword: String) {
        if (!isInitialized) {
            isInitialized = true
            viewModelScope.launch(Dispatchers.IO) {
                getSelectedBookUseCase(keyword).collect {
                    it.handle(
                        onSuccess = ::onSuccess,
                        onLoading = ::onLoading,
                        onError = ::onError
                    )
                }
            }
        }
    }

    fun refresh(){
        isInitialized = false
    }

    private fun onError(message: String) {
        _uiState.update { state ->
            state.copy(
                isLoading = false,
                selectedBook = null,
                isFavorite = false,
                errorMessage = message
            )
        }
    }

    private fun onLoading() {
        _uiState.update { state ->
            state.copy(
                isLoading = true,
                isFavorite = false,
                selectedBook = null,
                errorMessage = null
            )
        }
    }

    private fun onSuccess(selectedBook: Items) {
        _uiState.update { state ->
            state.copy(
                isLoading = false,
                selectedBook = selectedBook,
                errorMessage = null
            )
        }
    }

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
                onLoading=::onFavoriteLoading
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
    fun onFavoriteLoading(){
        _dBRequestState.update { state ->
            state.copy(
                isLoading = true
            )
        }
    }
    fun successCheckedIsFavorite(isFavorite: Boolean){
        _dBRequestState.update { state ->
            state.copy(
                isLoading = false,
                favoriteResults = state.favoriteResults.toMutableList()
                    .apply { add(isFavorite) }
            )
        }
    }
    fun errorIdentifyingFavorites(message: String){
        _dBRequestState.update { state ->
            state.copy(
                isLoading = false,
                favoriteResults = state.favoriteResults.toMutableList()
                    .apply { add(null) },
                errorMessage = message
            )
        }
    }
    fun errorRequestToDB(message: String){
        _dBRequestState.update { state ->
            state.copy(
                errorMessage = message
            )
        }
    }
    fun onSuccessRequestToDB (){
        _dBRequestState.update { state ->
            state.copy(
                isLoading = false,
                errorMessage = null
            )
        }
    }

}