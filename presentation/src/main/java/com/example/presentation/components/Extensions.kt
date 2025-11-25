package com.example.presentation.components

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun Context.showToast(messageRes: String) {
    Toast.makeText(this, messageRes, Toast.LENGTH_SHORT).show()
}
