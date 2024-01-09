package com.example.presentation.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.presentation.screen.components.SearchView
import com.example.presentation.screen.components.palylist.Player
import com.example.presentation.navigation.panel.BottomPanel
import com.example.presentation.service.PlayerService
import com.example.presentation.viewmodels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



@SuppressLint("NewApi")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    var search by remember { mutableStateOf("") }
    var stats = remember { mutableStateOf(false) }
    var checkedState by remember { mutableStateOf(true) }

    val quantitiesMusic = mainViewModel.allMusic.observeAsState()
    val size = quantitiesMusic.value!!.size
    val position = mainViewModel.getData.observeAsState()
    val context = LocalContext.current

    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    DisposableEffect(backDispatcher){
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                stats.value = false
            }
        }

        backDispatcher?.addCallback(callback)

        onDispose {
            callback.remove()
        }
    }


    Scaffold(
        topBar = {
            when (stats.value){
                false ->
                    Column(
                        modifier = modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        SearchView(search = search, onValueChange = { search = it })
                    }
                true ->
                    Text(text = "123")
            }
            
        },

        containerColor = Color.White,

        bottomBar = {
            Column {
                when (stats.value){
                    false ->
                        if (position.value?.position?.toInt() != null) {
                            Player(
                                it = position.value?.position!!.toInt(),
                                quantitiesMusic = quantitiesMusic,
                                navController = navController,
                                context = context,
                                mainViewModel = mainViewModel
                            )
                        }
                    true ->
                        Text(text = "123")

                }
                
                BottomPanel()
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
                    if (stats.value) {
                        Checkbox(
                            checked = checkedState,
                            onCheckedChange = {
                                checkedState = it },
                            )
                    }
                    Box(
                        modifier = modifier
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(Color(0xFFFBF7F7))
                            .fillMaxWidth()
                            .combinedClickable(
                                onClick = {
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
                                },
                                onLongClick = {
                                    stats.value = true

                                }
                            )
                            .padding(14.dp)
                    ) {
                        Column {
                            Text(text = quantitiesMusic.value!![music].nameMusic)
                            Text(
                                text = "Performer - Unknown",
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

            if (quantitiesMusic.value?.isEmpty() == true) {
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
    }
}


