package com.example.presentation.screens.components.items

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.domain.remote.models.Items
import com.example.presentation.R
import com.example.presentation.screens.components.showToast
import com.example.presentation.screens.searchscreen.SearchScreenViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookCard(
    currentBook: Items,
    searchViewModel: SearchScreenViewModel,
    onImageClick: () -> Unit
) {
    val context = LocalContext.current
    val dbState by searchViewModel.dBRequestState.collectAsStateWithLifecycle()
    val currentBookInfo = currentBook.volumeInfo
    val favoritesBooks by searchViewModel.favoriteResults.collectAsStateWithLifecycle()
    val isFavorite by rememberUpdatedState (favoritesBooks[currentBook.id])

    fun onFavoriteIconClick(isPressed: Boolean) {
        if (isPressed) {
            searchViewModel.deleteFavorite(currentBook.id ?: "")
            context.showToast(
                dbState.errorMessage
                    ?: context.getString(R.string.book_delete_sucsess)
            )
        } else {
            searchViewModel.addFavorite(
                bookId = currentBook.id ?: "",
                thumbnail = currentBookInfo?.imageLinks?.thumbnail
                    ?: "",
                authors = currentBookInfo?.authors?.joinToString()
                    ?: "",
                title = currentBookInfo?.title ?: ""
            )
            context.showToast(
                dbState.errorMessage
                    ?: context.getString(R.string.add_book_sucsess)
            )
        }
    }

    Column(
        modifier = Modifier
            .height(290.dp)
            .width(154.dp)
            .clickable {
                onImageClick()
            },
        verticalArrangement = Arrangement.spacedBy(4.dp)
    )
    {
        Box {
            GlideImage(
                contentScale = ContentScale.Crop,
                model = currentBook.volumeInfo?.imageLinks?.thumbnail,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentDescription = ""
            )
            Box(
                modifier = Modifier
                    .padding(top = 6.dp, end = 9.dp)
                    .align(Alignment.TopEnd)
            ) {
                FavoriteIcon(
                    isFavorite = isFavorite == true,
                    onImageClick = { isPressed -> onFavoriteIconClick(isPressed = isPressed) }
                )
            }
        }
        Text(
            text = currentBook.volumeInfo?.authors?.firstOrNull()
                ?: stringResource(R.string.no_authors),
            color = Color.Gray
        )
        Text(text = currentBook.volumeInfo?.title ?: "")
    }

}

data class BookCardState(
    var isLoading: Boolean = false,
    val isFavorite: Boolean? = null,
    var errorMessage: String? = null,
)
