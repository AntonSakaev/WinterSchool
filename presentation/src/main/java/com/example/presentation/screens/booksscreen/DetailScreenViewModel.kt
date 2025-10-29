package com.example.presentation.screens.booksscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.local.db.Favorite
import com.example.domain.local.db.usecases.AddFavoriteUseCase
import com.example.domain.local.db.usecases.CheckIsFavoriteUseCase
import com.example.domain.local.db.usecases.DeleteFavoriteUseCase
import com.example.domain.remote.models.Items
import com.example.domain.remote.usecases.GetSelectedBookUseCase
import com.example.presentation.screens.utils.handle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val getSelectedBookUseCase: GetSelectedBookUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val checkIsFavoriteUseCase: CheckIsFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailScreenState())
    val uiState = _uiState.asStateFlow()

    fun getSelectedBook(keyword: String) {
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


    private fun onError(message: String) {
        _uiState.update { state ->
            state.copy(
                isLoading = false,
                selectedBook = null,
                isFavorite = null,
                errorMessage = message
            )
        }
    }

    private fun onLoading() {
        _uiState.update { state ->
            state.copy(
                isLoading = true,
                isFavorite = null,
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
        checkIsFavorite(selectedBook.id ?:"")
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
                onError = ::errorRequestToDB,
                onLoading = ::onFavoriteLoading
            )
        }
    }

    fun checkIsFavorite(bookId: String) {
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

    fun successCheckedIsFavorite(isFavorite: Boolean) {
        _uiState.update { state ->
            state.copy(
                isLoadingFromDB = false,
                isFavorite = isFavorite
            )
        }
    }

    fun onSuccessRequestToDB(bookId: String) {
        _uiState.update { state ->
            state.copy(
                isLoadingFromDB = false,
                errorMessage = null
            )
        }
       checkIsFavorite(bookId)
    }

    fun onFavoriteLoading() {
        _uiState.update { state ->
            state.copy(
                isLoadingFromDB = true,
                isFavorite = null
            )
        }
    }

    fun errorRequestToDB(message: String) {
        _uiState.update { state ->
            state.copy(
                isLoading = false,
                isFavorite = null,
                errorMessage = message
            )
        }
    }
    fun clearFavorite() {
        _uiState.update {
            it.copy(
                isFavorite = null,
                errorMessage = null
            )
        }
    }
}