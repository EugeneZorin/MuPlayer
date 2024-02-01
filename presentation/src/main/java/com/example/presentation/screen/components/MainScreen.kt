package com.example.presentation.screen.components

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.presentation.viewmodels.MainViewModel
import com.example.presentation.viewmodels.ViewModelPlayList


@SuppressLint("NewApi")
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    viewModelPlayList: ViewModelPlayList,
    navController: NavController,
) {

    val context = LocalContext.current
    val quantitiesMusic = mainViewModel.allMusic.observeAsState()
    val selectedTrackNumber = mainViewModel.getData.observeAsState()

    // Checks if there are tracks in the application database
    val checkQuantitiesMusic = quantitiesMusic.value?.isEmpty()

    MainDisplay(
        mainViewModel = mainViewModel,
        viewModelPlayList = viewModelPlayList,
        navController = navController,
        context = context,
        quantitiesMusic = quantitiesMusic,
        selectedTrackNumber = selectedTrackNumber
    )

    // Displays messages that there are no music tracks in the database (they were not found or deleted)
    if (checkQuantitiesMusic == true) {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = modifier
                    .align(Alignment.Center)
            )
            {
                Text(text = "Music not found")
            }
        }
    }

}


fun chooseMusic(
    isChecked: Boolean,
    music: Int,
    viewModelPlayList: ViewModelPlayList
) {
    when (isChecked) {
        true ->
            viewModelPlayList.arrayChosenMusic.add(music)

        false ->
            viewModelPlayList.arrayChosenMusic.remove(music)
    }
}
