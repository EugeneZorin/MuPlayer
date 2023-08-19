package com.example.presentation.screen.components.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MusicLine(){

    Box (
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(Color(0xFFFBF7F7))
            .fillMaxWidth()
            .padding(14.dp),
    ) {
        Column {
            Text(text = "Названия песни")
            Text(text = "Исполнитель - Неизвестен")
        }
    }

}