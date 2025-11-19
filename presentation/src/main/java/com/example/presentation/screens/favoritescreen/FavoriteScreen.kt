/*
package com.example.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.components.icons.ArrowBack
import com.example.presentation.components.items.BookCard
import com.example.presentation.screens.searchscreen.SearchScreenViewModel
import com.example.presentation.theme.SemiBold_18

@Composable
fun FavoriteScreen(
    innerPaddingValues: PaddingValues,
    searchViewModel: SearchScreenViewModel,
    onExitClick:()->Unit,
    onDetailClick: (selectedBookID: String) -> Unit,
) {
    Column(
        Modifier
            .padding(innerPaddingValues)
            .padding(top = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(48.dp)
        ) {
            Icon(
                imageVector = Icons.ArrowBack,
                contentDescription = stringResource(R.string.back),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable {
                         onExitClick()
                    })
            Text(
                text = stringResource(R.string.favorite),
                modifier = Modifier
                    .align(Alignment.Center),
                style = SemiBold_18
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                count = books?.size ?: 0,
                key = { index ->
                    val book = books?.get(index)
                    "${book?.id}_${favoriteResults[book?.id]}"
                })
            { booksIndex ->
                val item = books?.get(booksIndex)
                if (item != null) {
                    BookCard(
                        item,
                        searchViewModel,
                        onImageClick = { onDetailClick(books?.get(booksIndex)?.id ?: "") }
                    )
                }
            }
        }
    }
}*/
