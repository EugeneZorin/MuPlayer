package com.example.presentation.screen.components

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.domain.entity.CoreEntityModel
import com.example.domain.entity.PlayerEntityModel
import com.example.presentation.navigation.panel.BottomPanel
import com.example.presentation.navigation.panel.MusicInteractionPanel
import com.example.presentation.screen.components.palylist.Player
import com.example.presentation.service.PlayerService
import com.example.presentation.theme.White
import com.example.presentation.viewmodels.MainViewModel
import com.example.presentation.viewmodels.ViewModelPlayList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainDisplay(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    viewModelPlayList: ViewModelPlayList,
    navController: NavController,
    context: Context,
    quantitiesMusic: State<List<CoreEntityModel>?>,
    selectedTrackNumber: State<PlayerEntityModel?>
){
    // To track what type of clicks are taking place
    var stats by remember { mutableStateOf(false) }

    // Search
    var search by remember { mutableStateOf("") }

    // The number of music tracks in the received database
    val size = quantitiesMusic.value!!.size

    // Retrieves from the ViewModel the track number that was selected by the user for playback
    val getTrackNumber = selectedTrackNumber.value?.position?.toInt()

    val getValue = viewModelPlayList.isChecked.value!!

    // Records how many tracks were selected by long pressing,
    // it is necessary to display this value in the upper part of the application
    val sizeChosenMusic = viewModelPlayList.arrayChosenMusic.size

    // Getting the "back button click" event manager
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    // Implementation of the "back button click" event
    DisposableEffect(backDispatcher) {

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                stats = false
            }
        }

        backDispatcher?.addCallback(callback)

        onDispose {
            callback.remove()
        }
    }


    Scaffold(
        topBar = {
            when (stats) {
                false ->
                    Column(
                        modifier = modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        SearchView(search = search, onValueChange = { search = it })
                    }

                true ->
                    Box(
                        modifier = Modifier
                            .background(White)
                            .fillMaxWidth(1f)
                            .fillMaxHeight(0.05f)
                            .padding(10.dp)

                    ) {
                        Text(text = "Songs selected: $sizeChosenMusic")
                    }
            }

        },

        containerColor = Color.White,

        bottomBar = {
            Column {
                when (stats) {
                    false ->
                        if (getTrackNumber != null) {
                            Player(
                                selectedTrackNumber = getTrackNumber,
                                quantitiesMusic = quantitiesMusic,
                                navController = navController,
                                context = context,
                                mainViewModel = mainViewModel
                            )
                        }

                    true ->
                        MusicInteractionPanel(
                            viewModelPlayList = viewModelPlayList
                        )

                }

                BottomPanel(
                    navController = navController
                )
            }


        }

    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 17.dp)
                .background(Color.White),
        ) {

            Box(
                modifier = modifier
                    .padding(vertical = 5.dp, horizontal = 5.dp)
            ) {
                Text(
                    text = "Works: $size",
                    color = Color.Gray
                )

            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = modifier.padding(vertical = 5.dp, horizontal = 5.dp)
            ) {
                items(size) { music ->

                    var isCheckedMain by remember { mutableStateOf(getValue) }

                    Box(
                        modifier = modifier
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(Color(0xFFFBF7F7))
                            .fillMaxWidth()
                            .combinedClickable(
                                onClick = {
                                    if (!stats) {
                                        CoroutineScope(Dispatchers.IO).launch {
                                            mainViewModel.updateData(music)
                                            Intent(
                                                context,
                                                PlayerService::class.java
                                            ).also { service ->
                                                context.startForegroundService(service)
                                            }
                                            mainViewModel.updateExternalData(true)
                                        }
                                    } else {
                                        isCheckedMain = when (isCheckedMain) {
                                            true -> false
                                            false -> true
                                        }
                                        chooseMusic(
                                            isChecked = isCheckedMain,
                                            viewModelPlayList = viewModelPlayList,
                                            music = music
                                        )
                                    }
                                },

                                onLongClick = {
                                    stats = true
                                    isCheckedMain = when (isCheckedMain) {
                                        true -> false
                                        false -> true
                                    }
                                    chooseMusic(
                                        isChecked = isCheckedMain,
                                        viewModelPlayList = viewModelPlayList,
                                        music = music
                                    )
                                }
                            )
                            .padding(14.dp)

                    ) {

                        Row(
                            modifier = modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(text = quantitiesMusic.value!![music].nameMusic)
                                Text(
                                    text = "Performer - Unknown",
                                    color = Color.Gray
                                )
                            }

                            if (stats) {
                                Checkbox(
                                    checked = isCheckedMain,
                                    onCheckedChange = { checkBox ->
                                        isCheckedMain = checkBox
                                        chooseMusic(
                                            isChecked = isCheckedMain,
                                            viewModelPlayList = viewModelPlayList,
                                            music = music
                                        )

                                    },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
