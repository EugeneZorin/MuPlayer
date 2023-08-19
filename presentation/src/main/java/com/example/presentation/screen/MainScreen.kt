package com.example.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialogDefaults.containerColor
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
                verticalArrangement = Arrangement.Center
            ) {
                SearchView(search = search, onValueChange = { search = it })
            }
        },

        containerColor = Color.White,

        bottomBar = {
            BottomPanel()
        },

        ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 17.dp)
                .background(Color.White),
        ) {

            Box(
                modifier = Modifier
                    .padding(vertical = 5.dp)
            ) {
                Text(text = "123")
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                items(100) {
                    MusicLine()
                }
            }
        }

    }

}


