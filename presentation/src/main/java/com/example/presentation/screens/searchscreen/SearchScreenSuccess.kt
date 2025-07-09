package com.example.presentation.screens.searchscreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.domain.remote.models.Books
import com.example.presentation.screens.components.items.BookCard

@Composable
fun SearchScreenSuccess(
    booksInfo: Books?
) {
    val context = LocalContext.current
    val books by rememberSaveable { mutableStateOf((booksInfo?.items)) }
    val keys = remember(books) { books?.map { it.id } }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            count = books?.size ?: 0,
            key = { index -> keys?.get(index) ?: 0 })
        { booksIndex ->
            val volume = books?.get(booksIndex)?.volumeInfo

            BookCard(
                volume,
                onFavoriteClick = { Toast.makeText(context, "SDFSDFD", Toast.LENGTH_SHORT).show() },
                onImageClick = { Toast.makeText(context, "TTTTT", Toast.LENGTH_SHORT).show() }
            )

        }
    }
}