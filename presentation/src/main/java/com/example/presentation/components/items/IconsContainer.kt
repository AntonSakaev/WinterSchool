package com.example.presentation.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconsContainer (imageVector: ImageVector, contentDescription: String, onClick:() -> Unit){
    Box(
        modifier = Modifier.size(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.clickable {
                onClick()
            },
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
}