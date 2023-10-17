package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.components.palylist.PlayerStripe
import com.example.presentation.screen.MainScreen
import com.example.presentation.screen.PlayerScreen
import com.example.presentation.viewmodels.MainViewModel


@Composable
fun NavigationGraph() {

    val navController = rememberNavController()
    val mainViewModel = hiltViewModel<MainViewModel>()



    NavHost(navController = navController, startDestination = MainScreens.MAIN_SCREE) {
        composable(MainScreens.MAIN_SCREE) {
            MainScreen( mainViewModel = mainViewModel, navController = navController)
        }
        composable(MainScreens.PLAYER_SCREEN){
            PlayerScreen( navController = navController )
        }
        composable(
            MainScreens.PLAYER_STRIPE,
        ){
            PlayerStripe()
        }
    }
}