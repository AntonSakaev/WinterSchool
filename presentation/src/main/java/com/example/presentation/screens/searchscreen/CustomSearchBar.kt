package com.example.presentation.screens.searchscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.screens.components.icons.Clear
import com.example.presentation.screens.components.icons.Options
import com.example.presentation.screens.components.icons.Search
import com.example.presentation.screens.components.items.IconsContainer
import com.example.presentation.theme.Blue
import com.example.presentation.theme.LightGray
import com.example.presentation.theme.Rose


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(
    onValueChange: (String) -> Unit,
    onFilterClick: () -> Unit,
    isSettingsEnabled: Boolean
) {
    var searchText by rememberSaveable { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = Modifier
            .height(32.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        BasicTextField(
            modifier = Modifier.weight(1f),
            value = searchText,
            onValueChange = { value ->
                searchText = value
                onValueChange(searchText)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    // Действие при нажатии кнопки "Поиск"
                    onValueChange(searchText)
                }
            ),
            decorationBox = { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    contentPadding = PaddingValues(0.dp),
                    value = searchText,
                    innerTextField = innerTextField,
                    visualTransformation = VisualTransformation.None,
                    singleLine = true,
                    enabled = true,
                    shape = RoundedCornerShape(12.dp),
                    interactionSource = interactionSource,
                    placeholder = {
                        Text(
                            text = stringResource(R.string.enter_some_words)
                        )
                    },
                    leadingIcon = {
                        IconsContainer(Icons.Search, stringResource(R.string.search)) {}
                    },
                    trailingIcon = {
                        IconsContainer(Icons.Clear, stringResource(R.string.clear)) {
                            searchText = ""
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Rose, // Основной цвет фона
                        focusedContainerColor = Rose,   // Цвет при фокусе
                        disabledContainerColor = Rose,   // Цвет в выключенном состоянии
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            },
        )

        Box(
            modifier = Modifier
                .size(32.dp)
                .background(color = Rose, shape = RoundedCornerShape(12.dp))
                .clickable { onFilterClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Options,
                contentDescription = stringResource(R.string.filter),
                tint = if (!isSettingsEnabled) LightGray else Blue
            )
        }
    }
}
