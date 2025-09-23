package com.example.presentation.screens.booksscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.local.db.Favorite
import com.example.domain.local.db.usecases.AddFavoriteUseCase
import com.example.domain.local.db.usecases.DeleteFavoriteUseCase
import com.example.domain.local.db.usecases.GetFavoritesUseCase
import com.example.domain.local.db.usecases.IsFavoriteUseCase
import com.example.domain.remote.models.Items
import com.example.domain.remote.usecases.GetSelectedBookUseCase
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
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailScreenState())
    val uiState = _uiState.asStateFlow()

    private var isInitialized = false

    private val _favorites = MutableStateFlow<List<Favorite>>(emptyList())
    val favorites = _favorites.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _isFavorite = MutableStateFlow<Boolean?>(null)
    val isFavorite: StateFlow<Boolean?> = _isFavorite.asStateFlow()

//    fun checkIsFavorite(bookId: String) {
//        viewModelScope.launch {
//            try {
//                _isFavorite.value = isFavoriteUseCase(bookId)
//                _error.value = null
//            } catch (e: Exception) {
//                _error.value = "Ошибка проверки избранного: ${e.message}"
//            }
//        }
//    }

    fun clearError() {
        _error.value = null
    }


//    fun deleteFavorite(bookId: String) {
//        viewModelScope.launch {
//            try {
//                deleteFavoriteUseCase(bookId)
//                _error.value = null
//            } catch (e: Exception) {
//                _error.value = "Ошибка удаления из избранного: ${e.message}"
//            }
//        }
//    }

//    fun addFavorite(favorite: Favorite) {
//        viewModelScope.launch {
//            try {
//                addFavoriteUseCase(favorite)
//                  _error.value = null
//            } catch (e: Exception) {
//                _error.value = "Ошибка добавления в избранное: ${e.message}"
//            }
//        }
//    }

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