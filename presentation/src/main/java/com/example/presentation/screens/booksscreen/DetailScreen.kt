package com.example.presentation.screens.booksscreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.domain.remote.models.Items
import com.example.presentation.R
import com.example.presentation.screens.components.icons.ArrowBack
import com.example.presentation.screens.components.icons.Favorite
import com.example.presentation.screens.components.items.ProgressIndicator
import com.example.presentation.screens.components.screens.ErrorScreen
import com.example.presentation.screens.components.screens.ScreenStateHandler
import com.example.presentation.theme.Bold_16
import com.example.presentation.theme.Gray
import com.example.presentation.theme.LightGray
import com.example.presentation.theme.Red
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

    var isFavorite by remember { mutableStateOf(false) }
    var isPressed by remember { mutableStateOf(false) }

    val pressScale by animateFloatAsState(
        targetValue = if (isPressed) 1f else 0.9f,
        animationSpec = if (isPressed) {
            keyframes {
                durationMillis = 700
                0.7f at 100
                1.3f at 250
                0.8f at 400
                1.1f at 550
                1.0f at 700
            }
        } else {
            tween(durationMillis = 0)
        }
    )

    LaunchedEffect(Unit) {
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
                Box(modifier = Modifier.fillMaxSize())
                {
                    Icon(
                        imageVector = Icons.Favorite,
                        contentDescription = stringResource(R.string.favorite),
                        modifier = Modifier
                            .size(16.dp)
                            .align(Alignment.Center)
                            .scale(pressScale)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                isFavorite = !isFavorite
                                isPressed = !isPressed

                            },
                        tint = if (isFavorite) Red else LightGray
                    )
                }
            }
        }
        ScreenStateHandler(
            state = state,
            onRefresh = {
                detailScreenViewModel.refresh()
                detailScreenViewModel.getSelectedBook(bookId)
            },
            successContent = { detailState ->
                BookInfo(detailState.selectedBook)
            }
        )
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