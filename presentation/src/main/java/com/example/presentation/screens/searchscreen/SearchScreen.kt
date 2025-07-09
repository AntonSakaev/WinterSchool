package com.example.presentation.screens.searchscreen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.screens.components.icons.Clear
import com.example.presentation.theme.Blue
import com.example.presentation.theme.Regular_12

@Composable
fun SearchScreen(
    searchViewModel: SearchScreenViewModel,
    innerPaddingValues: PaddingValues,
    onSettingsClick: () -> Unit
) {

    LaunchedEffect(true) {
        searchViewModel.getSearchSettings()
    }

    Column(
        Modifier
            .padding(innerPaddingValues)
            .padding(top = 28.dp)
            .padding(horizontal = 20.dp)
    ) {

        CustomSearchBar(
            onValueChange = searchViewModel::keywordInput,
            onFilterClick = { onSettingsClick() }
        )

        Spacer(modifier = Modifier.height(20.dp))
        DisplaySettings(searchViewModel)
        SearchScreenStateActions(searchViewModel)
    }

}

@Composable
fun DisplaySettings(searchViewModel: SearchScreenViewModel) {
    val searchSettings by searchViewModel.searchParams.collectAsState()

    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        if (searchSettings.authorName != "") {
            item {
                ActiveSettings(searchSettings.authorName) {
                    searchViewModel.clearAuthorNameFilter()
                }
            }
        }

        if (searchSettings.sortByDate) {
            item {
                ActiveSettings(stringResource(R.string.sort_by_date)) {
                    searchViewModel.clearSortByDateFilter()
                }
            }
        }
        if (searchSettings.bestMatch) {
            item {
                ActiveSettings(stringResource(R.string.best_match)) {
                    searchViewModel.clearBestMatchFilter()
                }
            }
        }
    }
}

@Composable
fun ActiveSettings(settingsName: String, onClearPress: () -> Unit) {
    Box(
        modifier = Modifier
            .width(160.dp)
            .height(32.dp)
            .border(1.dp, Blue, RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                text = settingsName,
                style = Regular_12,

                )
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                imageVector = Icons.Clear,
                contentDescription = stringResource(R.string.clear),
                modifier = Modifier
                    .size(8.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { onClearPress() }
                    )
            )
        }
    }
}