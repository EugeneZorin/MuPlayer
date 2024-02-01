package com.example.presentation.screen.components

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.presentation.navigation.panel.BottomPanel
import com.example.presentation.theme.White
import com.example.presentation.viewmodels.ViewModelPlayList
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import kotlinx.coroutines.delay

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlaylistScreen(
    modifier: Modifier = Modifier,
    viewModelPlayList: ViewModelPlayList,
    navController: NavController,
) {

    val namePlaylist = viewModelPlayList.getNamePlaylist.observeAsState()




    // Search
    var search by remember { mutableStateOf("") }

    // To track what type of clicks are taking place
    var stats by remember { mutableStateOf(false) }

    val getValue = viewModelPlayList.isChecked.value!!

    val sizePlaylist = namePlaylist.value!!.size




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
                        Text(text = "Playlist selected: ")
                    }
            }

        },

        containerColor = Color.White,

        bottomBar = {
            Column {
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
                    text = "Playlist: $sizePlaylist",
                    color = Color.Gray
                )

            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = modifier.padding(vertical = 5.dp, horizontal = 5.dp)
            ) {
                items(sizePlaylist) { music ->

                    var isCheckedMain by remember { mutableStateOf(getValue) }

                    Box(
                        modifier = modifier
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(Color(0xFFFBF7F7))
                            .fillMaxWidth()
                            .combinedClickable(
                                onClick = {
                                    if (!stats) {

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
                                Text(text = namePlaylist.value!![music])
                                Text(
                                    text = "Number of compositions: ",
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

