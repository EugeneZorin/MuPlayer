package com.example.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.screen.components.palylist.PlayerStripe
import com.example.presentation.permissions.Permissions
import com.example.presentation.permissions.RequestPermission
import com.example.presentation.screen.components.MainScreen
import com.example.presentation.screen.dontworking.PlayerScreen
import com.example.presentation.viewmodels.MainViewModel
import com.example.presentation.viewmodels.ViewModelPlayList


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph() {

    val navController = rememberNavController()
    val mainViewModel = hiltViewModel<MainViewModel>()
    val viewModelPlayList = hiltViewModel<ViewModelPlayList>()


    NavHost(navController = navController, startDestination = MainScreens.PERMISSION_REQUEST) {

        composable(MainScreens.PERMISSION_REQUEST) {
            RequestPermission(
                mainViewModel = mainViewModel,
                requestPermission = Permissions.readExternalPermission,
                navController = navController,

            )
        }

        composable(MainScreens.MAIN_SCREE) {
            MainScreen(
                mainViewModel = mainViewModel,
                viewModelPlayList = viewModelPlayList,
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