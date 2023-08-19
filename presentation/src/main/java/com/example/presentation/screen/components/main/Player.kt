package com.example.presentation.screen.components.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.presentation.R

@Composable
fun Player() {
    Box (
        modifier = Modifier
            .background(Color(0xFFE1EAF2))
            .fillMaxWidth()
            .padding(14.dp),
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column {
                Text(text = "Названия песни ")
                Text(
                    text = "Исполнитель - Неизвестен",
                    color = Color.Gray
                )
            }

            Box {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_pause),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }

            Box {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_skip_next),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }

        }


    }
}