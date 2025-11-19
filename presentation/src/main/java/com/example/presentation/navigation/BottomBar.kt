package com.example.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.presentation.R
import com.example.presentation.components.icons.Favorite
import com.example.presentation.components.icons.Search
import com.example.presentation.theme.LightGray
import com.example.presentation.theme.Blue

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val bottomItems = listOf(
        BottomItem(
            stringResource(R.string.search),
            Screens.SearchScreen,
            Icons.Search,
            Blue
        ),
        BottomItem(
            stringResource(R.string.favorite),
            Screens.FavoriteScreen,
            Icons.Favorite,
            Blue
        )
    )
    val backStackEntry by navController.currentBackStackEntryAsState()

    Column {
        Box(modifier = Modifier.height(1.dp).fillMaxWidth().background(LightGray))
        Row(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            bottomItems.forEach { item ->
                val isSelected = backStackEntry?.destination?.hasRoute(item.route::class)
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null // отключает ripple-эффект
                        ) {
                            navController.navigate(item.route)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Box(
                        modifier = Modifier.size(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name,
                            tint = if (isSelected == true) item.selectedColor else LightGray
                        )
                    }
                    Text(
                        text = item.name,
                        color = if (isSelected == true) Blue else LightGray
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .height(16.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(72.dp)
                    .height(2.dp)
                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(14.dp)
                    )
            )
        }
    }
}

data class BottomItem<T : Any>(
    val name: String,
    val route: T,
    val icon: ImageVector,
    val selectedColor: Color
)