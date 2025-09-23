package com.example.presentation.screens.components.items

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.domain.remote.models.Items
import com.example.presentation.R
import com.example.presentation.screens.components.icons.Favorite
import com.example.presentation.screens.searchscreen.SearchScreenViewModel
import com.example.presentation.theme.LightGray
import com.example.presentation.theme.Red

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookCard(
    currentBook: Items,
    searchViewModel: SearchScreenViewModel,
   // onFavoriteClick: () -> Unit,
    onImageClick: () -> Unit
) {
    val context = LocalContext.current
  //  var isFavorite by remember { mutableStateOf(false) }
    var isPressed by remember { mutableStateOf(false) }
    val isFavorite by  searchViewModel.isFavorite.collectAsState()

    // Анимация при нажатии
    val pressScale by animateFloatAsState(
        targetValue = if (isPressed) 1f else 0.9f,
        animationSpec = if (isPressed) {
            keyframes {
                durationMillis = 700
                0.7f at 100
                1.3f at 250
                0.8f at 400
                1.1f at 550
                1.0f at 700
            }
        } else
        {
            tween(durationMillis = 0)
        }
    )

    LaunchedEffect(isPressed) {
        searchViewModel.checkIsFavorite(currentBook.id ?: "")
        Log.d("TAG", "BookCard: $isFavorite")
    }


    Column(
        modifier = Modifier
            .height(290.dp)
            .width(154.dp)
            .clickable {
                onImageClick()
            },
        verticalArrangement = Arrangement.spacedBy(4.dp)
    )
    {
        Box {
            GlideImage(
                contentScale = ContentScale.Crop,
                model = currentBook.volumeInfo?.imageLinks?.thumbnail,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentDescription = ""
            )
            Box(
                modifier = Modifier
                    .padding(top = 6.dp, end = 9.dp)
                    .align(Alignment.TopEnd)
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(color = Color.White, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Favorite,
                        contentDescription = stringResource(R.string.favorite),
                        modifier = Modifier
                            .size(12.dp)
                            .scale(pressScale)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                if (isFavorite == true) {
                                    searchViewModel.deleteFavorite(currentBook.id ?: "")
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.book_delete_sucsess),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    searchViewModel.addFavorite(currentBook.id, currentBook.volumeInfo.imageLinks.thumbnail, currentBook.volumeInfo.authors, currentBook.volumeInfo.title)
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.add_book_sucsess),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                isPressed = !isPressed
                            },
                        tint = if (isFavorite == true) Red else LightGray
                    )
                }
            }
        }
        (
                if (currentBook.volumeInfo?.authors?.isNotEmpty() == true)
            currentBook.volumeInfo?.authors?.first()
        else stringResource(
            R.string.no_authors
        )
                ).let { Text(text = it ?:"", color = Color.Gray) }
        Text(text = currentBook.volumeInfo?.title ?: "")
    }
}