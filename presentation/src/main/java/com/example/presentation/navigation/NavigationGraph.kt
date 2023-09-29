package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.screen.MainScreen
import com.example.presentation.viewmodels.MainViewModel


@Composable
fun NavigationGraph() {

    val navController = rememberNavController()
    val mainViewModel = hiltViewModel<MainViewModel>()

    NavHost(navController = navController, startDestination = "main_screen") {
        composable("main_screen") {
            MainScreen( mainViewModel = mainViewModel )
        }
    }
}