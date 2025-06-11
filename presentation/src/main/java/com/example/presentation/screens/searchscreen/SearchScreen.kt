package com.example.presentation.screens.searchscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SearchScreen(
    searchViewModel: SearchScreenViewModel,
    innerPaddingValues: PaddingValues
) {

    Column(
        Modifier
            .padding(innerPaddingValues)
            .padding(horizontal = 20.dp)
    ) {
        CustomSearchBar(
            onValueChange = searchViewModel::keywordInput,
            onFilterClick = { Settings() }
        )
        Spacer(modifier = Modifier.height(20.dp))
        SearchScreenStateActions(searchViewModel)
    }
}