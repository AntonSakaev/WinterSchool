package com.example.presentation.screens.searchscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.screens.components.icons.Clear
import com.example.presentation.screens.components.icons.More
import com.example.presentation.screens.components.items.IconsContainer
import com.example.presentation.theme.Blue
import com.example.presentation.theme.Bold_16
import com.example.presentation.theme.Bold_18
import com.example.presentation.theme.LightGray
import com.example.presentation.theme.Regular_14
import com.example.presentation.theme.Regular_16
import com.example.presentation.theme.Rose
import com.example.presentation.theme.White
import kotlinx.coroutines.delay

private data class SearchFilterParams(
    val sortByDate: Boolean, val bestMatch: Boolean
)

@Composable
fun Settings(
    innerPaddingValues: PaddingValues,
    searchViewModel: SearchScreenViewModel,
    onDismiss: () -> Unit
) {
    val params by searchViewModel.searchParams.collectAsState()
    var searchText by rememberSaveable { mutableStateOf(params.authorName) }
    var filterParams by remember {
        mutableStateOf(
            SearchFilterParams(
                params.sortByDate,
                params.bestMatch
            )
        )
    }

    val interactionSource = remember { MutableInteractionSource() }
    var visible by remember { mutableStateOf(false) }
    var applyButtonIsEnabled by remember { mutableStateOf(false) }


    //Для запуска анимации при входе меням visible
    LaunchedEffect(true) {
        visible = true
        searchViewModel.getSearchSettings()
    }

    //задержка для отыгрывания анимации перед закрытием окна
    LaunchedEffect(visible) {
        if (!visible) {
            delay(10)
            onDismiss()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPaddingValues)
            .padding(top = 28.dp)
            .background(Color.Black.copy(alpha = 0.4f))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { visible = false }
            ),
        contentAlignment = Alignment.TopCenter,
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(initialOffsetY = { -it }),
            exit = slideOutVertically(targetOffsetY = { -it }),
            modifier = Modifier.fillMaxWidth()
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = {} // Пустой обработчик для блокировки кликов по поверхности
                    ),
                color = White,
                shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp),
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {

                    Title { visible = false }
                    SearchField(
                        searchText = searchText,
                        onValueChange = {
                            searchText = it
                            applyButtonIsEnabled = true
                        },
                        interactionSource = interactionSource
                    )

                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = stringResource(R.string.sort_by),
                        style = Bold_16
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    FilterRow(
                        filterParams = filterParams,
                        onFilterChange = {
                            filterParams = it
                            applyButtonIsEnabled = true
                        }
                    )
                    ApplyButton(
                        enabled = applyButtonIsEnabled,
                        onClick = {
                            searchViewModel.saveSearchSettings(
                                searchText,
                                filterParams.sortByDate,
                                filterParams.bestMatch
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Title(onExitClick: () -> Unit) {
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
            contentDescription = stringResource(R.string.clear),
            modifier = Modifier.clickable {
                onExitClick()
            }
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
private fun FilterRow(
    filterParams: SearchFilterParams,
    onFilterChange: (SearchFilterParams) -> Unit
) {
    Row {
        FilterChip(
            text = stringResource(R.string.by_data),
            selected = filterParams.sortByDate,
            onSelect = { selected ->
                onFilterChange(filterParams.copy(sortByDate = selected))
            }
        )
        Spacer(modifier = Modifier.width(12.dp))
        FilterChip(
            text = stringResource(R.string.best_match),
            selected = filterParams.bestMatch,
            onSelect = { selected ->
                onFilterChange(filterParams.copy(bestMatch = selected))
            }
        )
    }
}

@Composable
private fun ApplyButton(
    enabled: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 22.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled
            ) {
                if (enabled) onClick()
            }
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