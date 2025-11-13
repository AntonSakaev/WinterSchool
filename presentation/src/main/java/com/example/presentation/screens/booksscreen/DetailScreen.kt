package com.example.presentation.screens.booksscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.domain.remote.models.Items
import com.example.presentation.R
import com.example.presentation.screens.components.icons.ArrowBack
import com.example.presentation.screens.components.items.FavoriteIcon
import com.example.presentation.screens.components.items.ProgressIndicator
import com.example.presentation.screens.components.screens.ErrorScreen
import com.example.presentation.screens.components.showToast
import com.example.presentation.theme.Bold_16
import com.example.presentation.theme.Gray
import com.example.presentation.theme.Regular_14
import com.example.presentation.theme.Regular_16
import com.example.presentation.theme.Rose

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    innerPaddingValues: PaddingValues,
    bookId: String,
    detailScreenViewModel: DetailScreenViewModel,
    onExitClick: () -> Unit
) {

    val state by detailScreenViewModel.uiState.collectAsStateWithLifecycle()
    val dBState by detailScreenViewModel.dBRequestState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    fun onFavoriteIconClick(isPressed: Boolean) {
        if (isPressed) {
            detailScreenViewModel.deleteFavorite(state.selectedBook?.id ?: "")
            context.showToast(
                state.errorMessage
                    ?: context.getString(R.string.book_delete_sucsess)
            )
        } else {
            detailScreenViewModel.addFavorite(
                bookId = state.selectedBook?.id ?: "",
                thumbnail = state.selectedBook?.volumeInfo?.imageLinks?.thumbnail
                    ?: "",
                authors = state.selectedBook?.volumeInfo?.authors?.joinToString()
                    ?: "",
                title = state.selectedBook?.volumeInfo?.title ?: ""
            )
            context.showToast(
                state.errorMessage
                    ?: context.getString(R.string.add_book_sucsess)
            )
        }
    }

    LaunchedEffect(bookId) {
        detailScreenViewModel.getSelectedBook(bookId)
    }

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
            Card(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterEnd),
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            )
            {
                when {
                    !dBState.isLoading &&  dBState.isFavorite != null -> {
                        FavoriteIcon(
                            isFavorite = dBState.isFavorite == true,
                            onImageClick = { isPressed -> onFavoriteIconClick(isPressed) })
                    }
                }
            }
        }
        when {
            state.isLoading -> {
                ProgressIndicator()
            }

            state.errorMessage != null -> {
                ErrorScreen(
                    onClick = {
                        detailScreenViewModel.getSelectedBook(bookId)
                    }
                )
            }

            else -> {
                BookInfo(state.selectedBook)
            }
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookInfo(selectedBook: Items?) {
    val bookInfo = selectedBook?.volumeInfo
    GlideImage(
        model = bookInfo?.imageLinks?.thumbnail,
        contentDescription = stringResource(R.string.book_image),
        modifier = Modifier
            .height(300.dp)
            .width(200.dp)
            .padding(top = 8.dp)
            .clip(shape = RoundedCornerShape(24.dp)),
        contentScale = ContentScale.FillBounds
    )
    Text(
        text = bookInfo?.authors?.joinToString() ?: "",
        modifier = Modifier
            .padding(top = 14.dp)
            .padding(horizontal = 24.dp),
        style = Regular_16.copy(color = Gray),
    )
    Text(
        text = bookInfo?.title ?: "",
        modifier = Modifier
            .padding(top = 8.dp)
            .padding(horizontal = 24.dp),
        style = Bold_16,
    )
    Text(
        text = bookInfo?.publishedDate ?: "",
        modifier = Modifier.padding(top = 8.dp),
        style = Regular_14.copy(color = Gray)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 22.dp)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 32.dp,
                    topEnd = 32.dp
                )
            )
            .background(Rose)

    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                modifier = Modifier.padding(top = 26.dp),
                text = stringResource(R.string.description),
                style = Bold_16
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = bookInfo?.description ?: "",
                style = Regular_14,
                lineHeight = 20.sp
            )
        }
    }
}