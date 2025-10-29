package com.example.presentation.screens.components.items

import android.util.Log
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.screens.components.icons.Favorite
import com.example.presentation.theme.LightGray
import com.example.presentation.theme.Red

@Composable
fun FavoriteIcon(
    isFavorite: Boolean,
    onImageClick: (isPressed: Boolean) -> Unit
) {
    var isPressed by remember { mutableStateOf(isFavorite) }

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
}}