package com.example.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentation.panel.BottomPanel
import com.example.presentation.screen.components.SearchView
import com.example.presentation.screen.components.main.Player
import com.example.presentation.screen.components.main.SongColumn

@Preview(showBackground = true)
@Composable
fun MainScreen() {

    var search by remember { mutableStateOf("") }


    Scaffold(

        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                SearchView(search = search, onValueChange = { search = it })
            }
        },

        containerColor = Color.White,

        bottomBar = {
            Column {
                Player()
                BottomPanel()
            }

        },

        ) { SongColumn(it) }

}


