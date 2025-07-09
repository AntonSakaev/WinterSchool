package com.example.presentation.screens.searchscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.R
import com.example.presentation.screens.components.items.ProgressIndicator
import com.example.presentation.screens.components.screens.ErrorScreen

@Composable
fun SearchScreenStateActions(searchViewModel: SearchScreenViewModel) {
    val state by searchViewModel.uiState.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current

    when {
        state.isNoKeyWord -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(R.string.Enter_the_title_of_the_book_you_are_looking_for))
            }
        }

        state.isLoading -> {
            keyboardController?.hide() // Скрываем клавиатуру
            ProgressIndicator()
        }

        state.errorMessage != null -> {
            ErrorScreen (
                onClick = searchViewModel::refresh
                )
        }

        else -> {
            SearchScreenSuccess(state.postBooks)
        }
    }
}