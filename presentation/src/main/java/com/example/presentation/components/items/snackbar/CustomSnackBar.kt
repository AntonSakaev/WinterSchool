package com.example.presentation.components.items.snackbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.presentation.theme.Blue
import com.example.presentation.theme.Red
import com.example.presentation.theme.Regular_14
import com.example.presentation.theme.White

@Composable
fun CustomSnackbar(
    data: SnackbarData
) {
    val isError = (data.visuals as? CustomSnackbarVisuals)?.isError ?: false

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(12.dp),
        color = if (isError) Red else Blue,
        shadowElevation = 6.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 22.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = data.visuals.message,
                modifier = Modifier.weight(1f),
                style = Regular_14.copy(color = White)
            )
            Box(modifier = Modifier.size(24.dp)) {
                Icon(
                    modifier = Modifier.clickable {data.dismiss()},

                    contentDescription = "Закрыть",
                    tint = White,
                    imageVector = Icons.Default.Close,
                )
            }
        }
    }
}
    data class CustomSnackbarVisuals(
        override val actionLabel: String,
        override val duration: SnackbarDuration,
        override val message: String,
        override val withDismissAction: Boolean,
        val isError: Boolean,
    ) : SnackbarVisuals

    data class SnackbarViewEvent(
        val message: String,
        val isError: Boolean
    )