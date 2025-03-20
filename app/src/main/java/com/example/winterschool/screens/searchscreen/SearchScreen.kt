package com.example.winterschool.screens.searchscreen

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SearchScreen() {
    val searchViewModel: SearchScreenViewModel = hiltViewModel()
    val state by searchViewModel.uiState.collectAsStateWithLifecycle()
    val errorMessage = state.errorMessage
    var searchText by rememberSaveable { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
Column {
    BasicTextField(
        modifier = Modifier
            .height(32.dp)
            .fillMaxWidth(),
        value = searchText,
        onValueChange = { value ->
            searchText = value
            searchViewModel.keywordInput(searchText)
        },
        decorationBox = { innerTextField ->
            TextFieldDefaults.DecorationBox(
                contentPadding = PaddingValues(0.dp),
                value = searchText,
                innerTextField = innerTextField,
                visualTransformation = VisualTransformation.None,
                singleLine = true,
                enabled = true,
                shape = RoundedCornerShape(56.dp),
                interactionSource = interactionSource,
                placeholder = {
                    Text(
                        text = "HARDCODE"
                    )
                },
                leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "")
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        },
    )
}
}