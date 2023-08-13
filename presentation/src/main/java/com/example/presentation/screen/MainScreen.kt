package com.example.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.presentation.panel.BottomPanel
import com.example.presentation.screen.components.main.SearchView
import com.example.presentation.screen.components.main.SongColumn

@Composable
fun MainScreen() {

    var search by remember { mutableStateOf("") }



    Column(
        modifier = Modifier
            .fillMaxSize(),

    ) {



        Box(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            SearchView(
                search = search,
                onValueChange = {
                    search = it
            })
        }

        Box(modifier = Modifier){
            BottomPanel()
        }



    }


}

