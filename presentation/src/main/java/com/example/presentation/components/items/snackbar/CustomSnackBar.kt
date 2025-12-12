package com.example.presentation.components.items.snackbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.presentation.theme.Blue
import com.example.presentation.theme.Red

@Composable
fun CustomSnackbar(
    data: SnackbarData
) {
    val isError = (data.visuals as? CustomSnackbarVisuals)?.isError ?: false
    Snackbar(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .clip(RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        snackbarData = data,
        containerColor = if (isError) Red else Blue,
        actionOnNewLine = true
    )
}

data class CustomSnackbarVisuals(
    override val actionLabel: String?,
    override val duration: SnackbarDuration,
    override val message: String,
    override val withDismissAction: Boolean,
    val isError: Boolean,
) : SnackbarVisuals

data class SnackbarViewEvent(
    val message: String,
    val isError: Boolean
)