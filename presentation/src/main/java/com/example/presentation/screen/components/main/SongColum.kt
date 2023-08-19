package com.example.presentation.screen.components.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SongColumn(it: PaddingValues) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .padding(horizontal = 17.dp)
            .background(Color.White),
    ) {

        Box(
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 5.dp)
        ) {
            Text(
                text = "Произведений: 51",
                color = Color.Gray
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            items(100) {
                Box (
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(Color(0xFFFBF7F7))
                        .fillMaxWidth()
                        .padding(14.dp),
                ) {
                    Column {
                        Text(text = "Названия песни ")
                        Text(
                            text = "Исполнитель - Неизвестен",
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}