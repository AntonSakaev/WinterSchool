package com.example.presentation

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.example.presentation.components.items.snackbar.CustomSnackbar
import com.example.presentation.navigation.NavigationGraph
import com.example.presentation.navigation.BottomBar
import com.example.presentation.theme.Blue
import com.example.presentation.theme.Red
import com.example.presentation.theme.WinterSchoolTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val snackbarHostState = remember { SnackbarHostState() }

            HideSystemBars()
            WinterSchoolTheme {
                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState)
                        { data ->
                            CustomSnackbar(data = data)
                        }
                    },
                    containerColor = Color.White,
                    bottomBar = { BottomBar(navController) }
                ) { innerPadding ->
                    NavigationGraph(navController, innerPadding, snackbarHostState)
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
