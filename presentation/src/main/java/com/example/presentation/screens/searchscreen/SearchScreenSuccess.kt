package com.example.presentation.screens.searchscreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.screens.components.items.BookCard

@Composable
fun SearchScreenSuccess(
    searchViewModel: SearchScreenViewModel,
    onDetailClick: (selectedBookID: String) -> Unit
) {

    val state by searchViewModel.uiState.collectAsStateWithLifecycle()

    val favoritesBooks by searchViewModel.favoriteResults.collectAsStateWithLifecycle()

    val books by remember(state.postBooks?.items) {
        derivedStateOf { state.postBooks?.items }
    }

    val keys by remember(books) {
        derivedStateOf { books?.map { it.id } ?: emptyList() }
    }

    OnResumeEffect { searchViewModel. }

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
            val isFavorite = favoritesBooks.values.toMutableList().getOrNull(booksIndex)
            Log.d("TAG", "SearchScreenSuccess: ${favoritesBooks}")
            if (item != null && isFavorite != null) {
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

@Composable
fun OnResumeEffect(onResume: () -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                onResume()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}