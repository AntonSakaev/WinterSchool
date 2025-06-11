package com.example.presentation.screens.searchscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.screens.components.icons.Clear
import com.example.presentation.screens.components.icons.More
import com.example.presentation.screens.components.items.IconsContainer
import com.example.presentation.theme.Blue
import com.example.presentation.theme.Bold_16
import com.example.presentation.theme.Bold_18
import com.example.presentation.theme.Gray
import com.example.presentation.theme.LightGray
import com.example.presentation.theme.Regular_14
import com.example.presentation.theme.Regular_16
import com.example.presentation.theme.Rose
import com.example.presentation.theme.White

@Preview
@Composable
fun Settings(
//    isVisible: Boolean,
//                 onDismiss: () -> Unit,
//                 content: @Composable () -> Unit
) {
    // if (isVisible) {
    var searchText by rememberSaveable { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    var sortByDate by remember { mutableStateOf(false) }
    var bestMatch by remember { mutableStateOf(false) }

    val applyButtonIsEnabled by remember { mutableStateOf(bestMatch || sortByDate || searchText.isNotEmpty()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.2f))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {/* onDismiss()*/ }, // Закрытие при клике вне окна
        contentAlignment = Alignment.TopCenter,
    ) {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            color = White,
            shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp),
            shadowElevation = 8.dp,

            ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {

                Title()

                SearchField(
                    searchText = searchText,
                    onValueChange = { searchText = it },
                    interactionSource = interactionSource
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = stringResource(R.string.sort_by),
                    style = Bold_16
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    FilterChip(
                        text = stringResource(R.string.by_data),
                        selected = sortByDate,
                        onSelect = { sortByDate = it }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    FilterChip(
                        text = stringResource(R.string.best_match),
                        selected = bestMatch,
                        onSelect = { bestMatch = it }
                    )
                }

                ApplyButton(enabled = applyButtonIsEnabled)
            }

        }
    }
}

@Composable
fun Title() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 26.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.filters),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            style = Bold_18
        )
        Icon(
            imageVector = Icons.Clear,
            contentDescription = stringResource(R.string.clear)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    searchText: String,
    onValueChange: (String) -> Unit,
    interactionSource: InteractionSource
) {
    Text(
        text = stringResource(R.string.authors),
        style = Bold_16
    )
    Spacer(modifier = Modifier.height(8.dp))
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .border(BorderStroke(1.dp, LightGray), RoundedCornerShape(12.dp)),
        value = searchText,
        onValueChange = onValueChange,
        decorationBox = { innerTextField ->
            TextFieldDefaults.DecorationBox(
                contentPadding = PaddingValues(start = 8.dp),
                value = searchText,
                innerTextField = innerTextField,
                visualTransformation = VisualTransformation.None,
                singleLine = true,
                enabled = true,
                interactionSource = interactionSource,
                placeholder = {
                    Text(
                        text = stringResource(R.string.enter_author),
                        style = Regular_14
                    )
                },
                trailingIcon = {
                    IconsContainer(
                        imageVector = Icons.More,
                        contentDescription = stringResource(R.string.more)
                    ) {
                    }
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent, // Основной цвет фона
                    focusedContainerColor = Color.Transparent,   // Цвет при фокусе
                    disabledContainerColor = Color.Transparent,   // Цвет в выключенном состоянии
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        },
    )
}

@Composable
private fun FilterChip(
    text: String,
    selected: Boolean,
    onSelect: (Boolean) -> Unit
) {
    Text(
        text = text,
        style = Regular_14,
        modifier = Modifier
            .border(1.dp, if (selected) Blue else LightGray, RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onSelect(!selected) }
    )
}

@Composable
private fun ApplyButton(enabled: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 22.dp)
    ) {
        Text(
            text = stringResource(R.string.apply),
            style = if (enabled) Regular_16.copy(color = White) else Regular_16,
            modifier = Modifier
                .align(Alignment.Center)
                .background(
                    color = if (enabled) Blue else Rose,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 24.dp, vertical = 10.dp)
        )
    }
}