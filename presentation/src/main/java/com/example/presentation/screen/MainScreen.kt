package com.example.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.panel.BottomPanel
import com.example.presentation.screen.components.main.MusicLine
import com.example.presentation.screen.components.main.SearchView

@Preview(showBackground = true)
@Composable
fun MainScreen() {

    var search by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                SearchView(search = search, onValueChange = { search = it })
            }
        },

        bottomBar = { BottomPanel() },

        ) {


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(15.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(5.dp),

            ) {
            items(100) {
                MusicLine()
            }
        }
    }

}


