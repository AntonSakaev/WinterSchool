package com.example.presentation.screens.components

import android.content.Context
import android.widget.Toast

fun Context.showToast(messageRes: String) {
    Toast.makeText(this, messageRes, Toast.LENGTH_SHORT).show()
}