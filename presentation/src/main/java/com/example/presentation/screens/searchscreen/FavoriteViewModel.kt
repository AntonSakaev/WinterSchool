
package com.example.presentation.screens.searchscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.local.db.Favorite
import com.example.domain.local.db.usecases.AddFavoriteUseCase
import com.example.domain.local.db.usecases.CheckIsFavoriteUseCase
import com.example.domain.local.db.usecases.DeleteFavoriteUseCase
import com.example.presentation.screens.components.items.BookCardState
import com.example.presentation.screens.utils.handle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class FavoriteViewModel (
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
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
                isFavorite = null,
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

}
