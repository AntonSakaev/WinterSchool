package com.example.presentation.components.items

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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.domain.local.db.BookInfo
import com.example.presentation.R
import com.example.presentation.screens.searchscreen.SearchScreenViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookCard(
    currentBook: BookInfo,
    searchViewModel: SearchScreenViewModel,
    onImageClick: () -> Unit,
    snackbarHostState: SnackbarHostState
) {

    val dbState by searchViewModel.dBRequestState.collectAsStateWithLifecycle()
    val favoritesBooks by searchViewModel.favoriteResults.collectAsStateWithLifecycle()
    val isFavorite by rememberUpdatedState(favoritesBooks[currentBook.bookId])
    Log.d("CURRENTBOOK", "BookCard: $currentBook")
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
                model = currentBook.imageUrl,
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
                    currentBook,
                    dbState.errorMessage,
                    searchViewModel,
                    snackbarHostState
                )
            }
        }
        Text(
            text = currentBook.authors?.takeIf { it.isNotBlank() }?.substringBefore(",")
                ?: stringResource(R.string.no_authors),
            color = Color.Gray,
            maxLines = 1
        )
        Text(text = currentBook.bookName?.takeIf { it.isNotBlank() } ?: stringResource(R.string.without_name))
    }
}


