package com.example.winterschool.screens.searchscreen

import android.util.Log
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.winterschool.R


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SearchScreen() {

    val context = LocalContext.current
    val searchViewModel: SearchScreenViewModel = hiltViewModel()
    val state by searchViewModel.uiState.collectAsStateWithLifecycle()
    val errorMessage = state.errorMessage
    var searchText by rememberSaveable { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    val progressValue by remember { mutableFloatStateOf(1.0f) }
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = progressValue,
        animationSpec = infiniteRepeatable(animation = tween(3000), RepeatMode.Reverse), label = ""
    )
    Column(
        Modifier
            .padding(top = 54.dp)
            .padding(horizontal = 26.dp)
    ) {
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
                            text = stringResource(R.string.enter_some_words)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = ""
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = ""
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            },
        )

        when {
            state.isNoKeyWord -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = stringResource(R.string.Enter_the_title_of_the_book_you_are_looking_for))
                }
                Log.d("SUCCESS", "SearchScreen: SUCCESS")
            }

            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.5f)
                ) {
                    CircularProgressIndicator(
                        progress = { progressAnimationValue },
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White,
                        trackColor = Color.Blue,
                        gapSize = (-5.0).dp
                    )
                }
                Log.d("isLoading", "SearchScreen: SUCCESS")
            }

            errorMessage != null -> {
                Log.d("errorMessage", "SearchScreen:${state.errorMessage}")
//                Column(
//                    modifier = Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text(modifier = Modifier.padding(12.dp), text = stringResource(R.string.error))
//                    Text(
//                        modifier = Modifier
//                            .clip(RoundedCornerShape(12.dp))
//                            .background(Color.Blue)
//                            .padding(vertical = 10.dp, horizontal = 24.dp)
//                            .clickable {
//                                searchViewModel.keywordInput("")
//                            },
//                        text = stringResource(R.string.try_again),
//                        color = Color.White
//                    )
//                }
            }

            else -> {
                SearchScreenSuccess(state.postBooks)
                Log.d("else", "SearchScreen: SUCCESS")
            }

        }
    }
}