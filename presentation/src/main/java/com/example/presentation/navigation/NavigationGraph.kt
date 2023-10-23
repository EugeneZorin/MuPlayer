package com.example.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.components.palylist.PlayerStripe
import com.example.presentation.permissions.Permissions
import com.example.presentation.permissions.RequestPermission
import com.example.presentation.screen.MainScreen
import com.example.presentation.screen.PlayerScreen
import com.example.presentation.viewmodels.MainViewModel


@Composable
fun NavigationGraph() {

    val navController = rememberNavController()
    val mainViewModel = hiltViewModel<MainViewModel>()


    NavHost(navController = navController, startDestination = MainScreens.PERMISSION_REQUEST) {

        composable(MainScreens.PERMISSION_REQUEST) {
            RequestPermission(
                mainViewModel = mainViewModel,
                requestPermission = Permissions.requestPermission,
                navController = navController,
            )
        }

        composable(MainScreens.MAIN_SCREE) {
            MainScreen(
                mainViewModel = mainViewModel,
                navController = navController,
            )
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