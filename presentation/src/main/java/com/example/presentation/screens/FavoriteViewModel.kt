package com.example.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.local.db.BookInfo
import com.example.domain.local.db.usecases.AddFavoriteUseCase
import com.example.domain.local.db.usecases.CheckIsFavoriteUseCase
import com.example.domain.local.db.usecases.DeleteFavoriteUseCase
import com.example.presentation.components.handle
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

    protected val _dBRequestState = MutableStateFlow(dBState())
    val dBRequestState = _dBRequestState.asStateFlow()

    protected val _favoriteResults = MutableStateFlow<MutableMap<String?, Boolean?>>(mutableMapOf())
    val favoriteResults = _favoriteResults.asStateFlow()

    fun addFavorite(bookInfo: BookInfo) {
        viewModelScope.launch {
            addFavoriteUseCase(bookInfo).handle(
                onSuccess = {
                    checkThisForFavorite(bookInfo.bookId)
                    onSuccessRequestToDB()
                },
                onError = ::onErrorRequestToDB,
                onLoading = ::onFavoriteLoading
            )
        }
    }

    fun deleteFavorite(bookId: String) {
        viewModelScope.launch {
            deleteFavoriteUseCase(bookId).handle(
                onSuccess = {
                    checkThisForFavorite(bookId)
                    onSuccessRequestToDB()
                },
                onError = ::onErrorRequestToDB
            )
        }
    }

    fun checkThisForFavorite(bookId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            checkIsFavoriteUseCase(bookId).collect {
                it.handle(
                    onLoading = ::onFavoriteLoading,
                    onSuccess = { isFavorite -> successCheckedIsFavorite(bookId, isFavorite) },
                    onError = ::onErrorRequestToDB
                )
            }
        }
    }

    fun successCheckedIsFavorite(bookId: String, isFavorite: Boolean) {
        _favoriteResults.update { currentList ->
            currentList.apply { put(bookId, isFavorite) }
        }
        _dBRequestState.update { state ->
            state.copy(
                isLoading = false,
            )
        }
    }

    fun onFavoriteLoading() {
        _dBRequestState.update { state ->
            state.copy(
                isLoading = true,
                errorMessage = null
            )
        }
    }

    fun onErrorRequestToDB(message: String) {
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

data class dBState(
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
)