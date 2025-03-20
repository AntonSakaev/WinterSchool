package com.example.winterschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.winterschool.navigation.NavigationGraph
import com.example.winterschool.screens.bottombar.BottomBar
import com.example.winterschool.ui.theme.WinterSchoolTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            WinterSchoolTheme {
                Scaffold(
                           bottomBar = { BottomBar(navController) }
                ) { innerPadding ->
                    NavigationGraph(navController)
                }
            }
        }
    }
}
