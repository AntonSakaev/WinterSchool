package com.example.winterschool.screens.bottombar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.winterschool.screens.components.icons.Favorite
import com.example.winterschool.screens.components.icons.Search


@Composable
fun BottomBar(
    navController: NavHostController
) {
    Row(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier
                .size(24.dp)
                .clickable {

                },
            imageVector = Icons.Search,
            tint = Color(0xFF00ACFF),
            contentDescription = ""
        )

        Icon(
            modifier = Modifier
                .size(24.dp)
                .clickable {

                },
            imageVector = Icons.Favorite,
            tint = Color(0xFFD9D9D9),
            contentDescription = ""
        )


    }
}