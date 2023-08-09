package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.presentation.screen.AlbumScreen
import com.example.presentation.screen.MainScreen
import com.example.presentation.screen.SettingScreen

@Composable
fun BottomNavigationMain(navController: NavHostController) {
    val items = listOf(
        MainScreen(),
        SettingScreen(),
        AlbumScreen()
    )


}