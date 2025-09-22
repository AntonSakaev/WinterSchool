package com.example.presentation.screens.booksscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.remote.models.Books
import com.example.domain.remote.models.Items
import com.example.domain.remote.usecases.GetSelectedBookUseCase
import com.example.presentation.screens.searchscreen.SearchScreenState
import com.example.presentation.screens.utils.handle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel@Inject constructor(
    private val getSelectedBookUseCase: GetSelectedBookUseCase
) :  ViewModel() {

    private val _uiState = MutableStateFlow(DetailScreenState())
    val uiState = _uiState.asStateFlow()

    fun getSelectedBook(keyword: String) {
        viewModelScope.launch (Dispatchers.IO){
                getSelectedBookUseCase(keyword).collect {
                it.handle(
                    onSuccess = ::onSuccess,
                    onLoading = ::onLoading,
                    onError = ::onError
                )
            }
      }}

    private fun onError(message: String) {
        _uiState.update { state ->
            state.copy(
                isLoading = false,
                selectedBook = null,
                errorMessage = message
            )
        }
    }

    private fun onLoading() {
        _uiState.update { state ->
            state.copy(
                isLoading = true,
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
}