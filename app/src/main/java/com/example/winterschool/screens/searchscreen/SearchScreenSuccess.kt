package com.example.winterschool.screens.searchscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.domain.models.Books
import com.example.winterschool.R

@OptIn(ExperimentalGlideComposeApi::class)

@Composable
fun SearchScreenSuccess(
    booksInfo: Books?
) {
    val books by rememberSaveable { mutableStateOf((booksInfo?.items)) }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(books?.size ?: 0) { booksIndex ->
            val volume = books?.get(booksIndex)?.volumeInfo
            Column(
                modifier = Modifier
                    .height(290.dp)
                    .width(154.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            )
            {
                GlideImage(
                    contentScale = ContentScale.Crop,
                    model = volume?.imageLinks?.thumbnail,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                        .background(color = Color.Green)
                        .clip(RoundedCornerShape(16.dp)),
                    contentDescription = ""
                )
                (if (volume?.authors?.isNotEmpty() == true) volume.authors.first() else stringResource(
                    R.string.no_authors
                )).let { Text(text = it, color = Color.Gray) }
                Text(text = volume?.title ?: "")
            }
        }
    }
}