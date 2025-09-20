package com.example.presentation

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.example.presentation.navigation.NavigationGraph
import com.example.presentation.screens.bottombar.BottomBar
import com.example.presentation.theme.WinterSchoolTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            HideSystemBars()
            WinterSchoolTheme {
                Scaffold(
                    containerColor = Color.White,
                           bottomBar = { BottomBar(navController) }
                ) { innerPadding ->
                    NavigationGraph(navController, innerPadding)
                }
            }
        }
    }
}

//скрываем системную панель навигации
@Composable
fun HideSystemBars() {
    val localContext = LocalContext.current
    val window = (localContext as Activity).window
    val insetsController = WindowCompat.getInsetsController(window, window.decorView)

    DisposableEffect(Unit) {
        insetsController.apply {
            hide(WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            // Устанавливаем черные индикаторы статус бара
            isAppearanceLightStatusBars = true
        }

        onDispose {
            // При необходимости возвращаем панель
            insetsController.show(WindowInsetsCompat.Type.navigationBars())
        }
    }
}
