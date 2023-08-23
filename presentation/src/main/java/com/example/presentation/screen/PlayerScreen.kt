package com.example.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.panel.BottomPanel

@Composable
fun PlayerScreen() {

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
                ) {
                    it
                    /*Image(painter = painterResource(id = R.drawable.baseline_pause), contentDescription = null)*/

                }
            }

            Column(
                modifier = Modifier.padding(vertical = 15.dp, horizontal = 5.dp)
            ) {

                Text(text = "Названия песни ")
                Text(
                    text = "Исполнитель - Неизвестен",
                    color = Color.Gray
                )
            }
        }


    }
}


