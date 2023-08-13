package com.example.presentation.screen.components.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SongColumn() {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {

        items(100) { index ->

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
                    .height(50.dp)
                    .clip(CircleShape)
                    .clickable { }
            ) {
                Text(
                    text = "Item $index",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}