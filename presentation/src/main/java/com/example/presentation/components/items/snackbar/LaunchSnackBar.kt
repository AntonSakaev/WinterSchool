package com.example.presentation.components.items.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun LaunchSnackBar(
    key: Any?,
    snackbarHostState: SnackbarHostState,
    message: String,
    isError: Boolean
) {
    LaunchedEffect(key) {
        snackbarHostState.showSnackbar(
            visuals = CustomSnackbarVisuals(
                actionLabel = null,
                duration = SnackbarDuration.Short,
                message = message,
                withDismissAction = true,
                isError = isError
            )
        )
    }
}