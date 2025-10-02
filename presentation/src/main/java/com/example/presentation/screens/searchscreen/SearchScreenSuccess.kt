package com.example.presentation.screens.searchscreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.screens.components.items.BookCard

@Composable
fun SearchScreenSuccess(
    searchViewModel: SearchScreenViewModel,
    onDetailClick: (selectedBookID: String) -> Unit
) {

    val state by searchViewModel.uiState.collectAsStateWithLifecycle()
    val favoritesBooks by searchViewModel.isFavorite.collectAsStateWithLifecycle()
    val books by rememberSaveable { mutableStateOf((state.postBooks?.items)) }
    val keys: List<String?> = remember(books) { books?.map { it.id } ?: emptyList() }

       LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            count = books?.size ?: 0,
            key = { index -> keys[index] ?: 0 })
        { booksIndex ->
            val item = books?.get(booksIndex)
            if (item != null) {
                val isFavorite = favoritesBooks.favoriteResults.getOrNull(booksIndex) == true
                Log.d("TAG", "SearchScreenSuccess: ${books!!.size}")
                Log.d("TAG", "SearchScreenSuccess: ${favoritesBooks.favoriteResults.size}")
                BookCard(
                    isFavorite,
                    item,
                    searchViewModel,
                    onImageClick = { onDetailClick(books?.get(booksIndex)?.id ?: "") }
                )
            }
        }
    }
}
