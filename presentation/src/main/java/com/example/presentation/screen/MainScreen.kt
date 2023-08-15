package com.example.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.presentation.panel.BottomPanel
import com.example.presentation.screen.components.main.SearchView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    var search by remember { mutableStateOf("") }

    Scaffold(

        topBar = {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    SearchView(
                        search = search,
                        onValueChange = {
                            search = it
                    })
                }
            }
        },

        bottomBar = {
            BottomPanel()
        }
    ) {
        it
    }




}

