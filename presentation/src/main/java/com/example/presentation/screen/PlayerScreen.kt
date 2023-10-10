package com.example.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.presentation.navigation.panel.BottomPanel
import com.example.presentation.components.palylist.PlayerStripe
import com.example.presentation.viewmodels.MainViewModel


@Composable
fun PlayerScreen(
    navController: NavController,
) {
    Scaffold(

        containerColor = Color.White,

        bottomBar = {
            Column {
                BottomPanel()
            }
        },

        ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .padding(horizontal = 30.dp, vertical = 50.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.75f)
                    .clip(shape = RoundedCornerShape(size = 20.dp))
            ) {
                Box(
                    modifier = Modifier

                        .fillMaxSize()
                        .background(Color(0xFFE1EAF2))
                ) {
                    it
                }
            }

            Column(
                modifier = Modifier.padding(vertical = 15.dp, horizontal = 5.dp)
            ) {

                Text(text = "Song titles: ")
                Text(
                    text = "Performer - Unknown",
                    color = Color.Gray
                )
            }

            Column {


                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        PlayerStripe()

                    }


                }

            }


        }


    }
}




