package com.example.presentation.components.items

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.domain.local.db.BookInfo
import com.example.presentation.R
import com.example.presentation.components.icons.Favorite
import com.example.presentation.components.items.snackbar.CustomSnackbarVisuals
import com.example.presentation.components.items.snackbar.LaunchSnackBar
import com.example.presentation.components.items.snackbar.SnackbarViewEvent
import com.example.presentation.screens.FavoriteViewModel
import com.example.presentation.theme.LightGray
import com.example.presentation.theme.Red

@Composable
fun FavoriteIcon(
    isFavorite: Boolean,
    bookInfo: BookInfo,
    ifError: String?,
    viewModel: FavoriteViewModel,
    snackbarHostState: SnackbarHostState
) {
    var isPressed by remember { mutableStateOf(isFavorite) }
    val context = LocalContext.current

    val snackBarEvents: MutableState<SnackbarViewEvent?> =
        remember { mutableStateOf(null) }

     fun triggerSnackbar(message: String, isError: Boolean) {
        snackBarEvents.value = SnackbarViewEvent(message, isError)
    }

    fun onImageClick(isPressed: Boolean) {
        if (isPressed) {
            viewModel.deleteFavorite(bookInfo.bookId.toString())
            triggerSnackbar(
                message = ifError ?: context.getString(R.string.book_delete_sucsess),
                isError = !ifError.isNullOrBlank()
            )
        } else {
            viewModel.addFavorite(bookInfo)
            triggerSnackbar(
                message = ifError ?: context.getString(R.string.add_book_sucsess),
                isError = !ifError.isNullOrBlank()
            )
        }
    }

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
    Box(
        modifier = Modifier
            .size(24.dp)
            .background(color = Color.White, shape = CircleShape),
        contentAlignment = Alignment.Center
    )
    {
        Icon(
            imageVector = Icons.Favorite,
            contentDescription = stringResource(R.string.favorite),
            modifier = Modifier
                .size(16.dp)
                .scale(pressScale)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    onImageClick(isPressed)
                    isPressed = !isPressed
                },
            tint = if (isPressed) Red else LightGray
        )
    }
    snackBarEvents.value?.let {
            LaunchSnackBar(
                key = isPressed,
                snackbarHostState = snackbarHostState,
                message = it.message,
                isError = it.isError
            )
    }
}