package com.example.presentation.screen

import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.presentation.components.SearchView
import com.example.presentation.components.palylist.Player
import com.example.presentation.navigation.panel.BottomPanel
import com.example.presentation.service.PlayerService
import com.example.presentation.viewmodels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    var search by remember { mutableStateOf("") }
    val quantitiesMusic = mainViewModel.allMusic.observeAsState()
    val position = mainViewModel.getData.observeAsState()
    val context = LocalContext.current





    Scaffold(
        topBar = {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                SearchView(search = search, onValueChange = { search = it })
            }
        },

        containerColor = Color.White,

        bottomBar = {
            Column {
               if (position.value != null) {
                   Player(
                       position.value?.position!!.toInt(), quantitiesMusic, navController
                   )
               }

                BottomPanel()
            }
        }

    ) { it ->
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
                    text = "Works: ${quantitiesMusic.value?.size}",
                    color = Color.Gray
                )

            }


            if (quantitiesMusic.value?.size != null) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = modifier
                        .padding(vertical = 5.dp, horizontal = 5.dp)
                ) {
                    items(quantitiesMusic.value!!.size) {
                        Box(
                            modifier = modifier
                                .clip(shape = RoundedCornerShape(10.dp))
                                .background(Color(0xFFFBF7F7))
                                .fillMaxWidth()
                                .clickable() {
                                    CoroutineScope(Dispatchers.IO).launch {
                                        mainViewModel.updateData(it)
                                        Intent(context, PlayerService::class.java).also { service ->
                                            context.startForegroundService(service)
                                        }
                                    }
                                }
                                .padding(14.dp)
                        ) {
                            Column {
                                Text(text = quantitiesMusic.value!![it].nameMusic)
                                Text(
                                    text = "Performer - Unknown",
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            } else {
                Box(modifier = modifier
                    .fillMaxSize()
                ) {
                    Box(modifier = modifier
                        .align(Alignment.Center))
                    {
                        Text(text = "Music not found")
                    }
                }
            }
        }
    }
}


