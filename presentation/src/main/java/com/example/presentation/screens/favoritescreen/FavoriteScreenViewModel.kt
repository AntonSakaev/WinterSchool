package com.example.presentation.screens.favoritescreen

import androidx.lifecycle.viewModelScope
import com.example.domain.local.db.BookInfo
import com.example.domain.local.db.usecases.AddFavoriteUseCase
import com.example.domain.local.db.usecases.CheckIsFavoriteUseCase
import com.example.domain.local.db.usecases.DeleteFavoriteUseCase
import com.example.domain.local.db.usecases.GetFavoritesUseCase
import com.example.presentation.components.handle
import com.example.presentation.screens.FavoriteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteScreenViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    checkIsFavoriteUseCase: CheckIsFavoriteUseCase,
    deleteFavoriteUseCase: DeleteFavoriteUseCase,
    addFavoriteUseCase: AddFavoriteUseCase,
) : FavoriteViewModel(
    checkIsFavoriteUseCase = checkIsFavoriteUseCase,
    addFavoriteUseCase = addFavoriteUseCase,
    deleteFavoriteUseCase = deleteFavoriteUseCase
) {
    private val _favoritesBooks = MutableStateFlow<List<BookInfo>>(emptyList())
    val favoritesBooks = _favoritesBooks.asStateFlow()

    fun getAllFavoritesBook() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoritesUseCase().collect {
                it.handle(
                    onLoading = ::onFavoriteLoading,
                    onSuccess = { listBooks ->
                        onSuccessRequestToDB()
                        _favoritesBooks.value = listBooks
                    },
                    onError = ::onErrorRequestToDB
                )
            }
        }
    }
}